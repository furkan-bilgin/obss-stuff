import React, { useEffect, useState } from 'react';
import { ErrorMessage } from '../ErrorMessage';
import { LoadingSpinner } from '../LoadingSpinner';
import ReactSelect from 'react-select';
import { FaEdit, FaPlus, FaTrash } from 'react-icons/fa';
import { RxValueNone } from 'react-icons/rx';

interface Entity {
  id?: number;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  [key: string]: any; // I could also use never but it messes up with id so I'll be leaving this here
}

interface EntityForm {
  id?: number;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  [key: string]: any;
}

interface FieldDataFetchOptions {
  id?: number;
  name: string;
}

type CRUDField<T extends Entity> = {
  name: string;
  label: string;
  type: 'text' | 'textarea' | 'number' | 'dropdown' | 'email' | 'multiselect';
  dataFetcher?: (
    options: FieldDataFetchOptions
  ) => Promise<unknown[] | undefined>;
  dataTransformer?: (form: EntityForm) => T;
  required?: boolean;
};

export interface CRUDConfig<T extends Entity> {
  entityName: string;
  initialEntity: T;
  getAll: () => Promise<T[]>;
  getById: (id: number) => Promise<T>;
  create: (entity: T) => Promise<void>;
  update: (id: number, entity: T) => Promise<void>;
  delete: (id: number) => Promise<void>;
  fields: CRUDField<T>[];
  tableColumns: {
    header: string;
    render: (entity: T) => React.ReactNode;
    className?: string;
  }[];
  canCreate?: boolean;
}

const GenericList = <T extends Entity>({
  config,
  onEdit,
  onDelete,
}: {
  config: CRUDConfig<T>;
  onEdit: (id: number) => void;
  onDelete: (id: number) => void;
}) => {
  const [entities, setEntities] = useState<T[]>([]);
  const [error, setError] = useState<Error | null>(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const data = await config.getAll();
        setEntities(data ?? []);
      } catch (err) {
        console.error(err);
        if (err instanceof Error) {
          setError(err);
        }
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  if (error) return <ErrorMessage error={error} />;
  if (loading) return <LoadingSpinner />;

  return (
    <table className="table w-full">
      <thead>
        <tr>
          {config.tableColumns.map((col) => (
            <th key={col.header} className={col.className}>
              {col.header}
            </th>
          ))}
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {entities.length === 0 && (
          <tr>
            <td
              colSpan={config.tableColumns.length + 1}
              className="text-center"
            >
              <div className="flex flex-row items-center justify-center gap-2">
                <RxValueNone />
                <span>No records found.</span>
              </div>
            </td>
          </tr>
        )}
        {entities.map((entity) => (
          <tr key={entity.id}>
            {config.tableColumns.map((col) => (
              <td key={col.header}>{col.render(entity)}</td>
            ))}
            <td>
              <button
                className="btn btn-sm btn-primary mr-2"
                onClick={() => onEdit(entity.id!)}
              >
                <FaEdit />
              </button>
              <button
                className="btn btn-sm btn-error"
                onClick={() => {
                  if (confirm('Are you sure you want to delete?')) {
                    onDelete(entity.id!);
                  }
                }}
              >
                <FaTrash />
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

const GenericForm = <T extends Entity>({
  config,
  entityId,
  onSuccess,
}: {
  config: CRUDConfig<T>;
  entityId?: number;
  onSuccess: () => void;
}) => {
  const [entity, setEntity] = useState<T>(config.initialEntity);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<Error | null>(null);
  const [fieldData, setFieldData] = useState<Record<string, T[]>>({});
  const isEdit = !!entityId;

  useEffect(() => {
    const fetchData = async () => {
      try {
        for (const field of config.fields) {
          if (field.dataFetcher) {
            const data = await field.dataFetcher({
              id: entityId,
              name: field.name.toString(),
            });
            if (data) {
              setFieldData((prev) => ({ ...prev, [field.name]: data as T[] }));
            }
          }
        }
        if (entityId) {
          setLoading(true);
          const data = await config.getById(entityId);
          setEntity(data ?? config.initialEntity);
        } else {
          setEntity(config.initialEntity);
        }
      } catch (err) {
        console.error(err);
        if (err instanceof Error) {
          setError(err);
        }
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [entityId, config]);

  if (error) return <ErrorMessage error={error} />;
  if (loading) return <LoadingSpinner />;

  const handleChange = (
    e: React.ChangeEvent<
      HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement
    >
  ) => {
    const { name, value } = e.target;
    setEntity((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    try {
      let transformedEntity = entity;
      for (const field of config.fields) {
        if (field.dataTransformer) {
          transformedEntity = field.dataTransformer(
            transformedEntity as EntityForm
          );
        }
        if (field.type === 'dropdown' && !entity[field.name]) {
          // Ensure dropdown value is null, because when cleared
          // it sets the value to ""
          (transformedEntity as Record<string, unknown>)[field.name] = null;
        }
      }
      console.log(transformedEntity);
      if (isEdit) {
        await config.update(entityId, transformedEntity);
      } else {
        await config.create(transformedEntity);
      }
      onSuccess();
    } catch (err) {
      console.error(err);
      if (err instanceof Error) {
        setError(err);
      }
    } finally {
      setLoading(false);
    }
  };

  const selectField = (field: CRUDField<T>) => {
    const options = fieldData[field.name]?.map((option) => ({
      value: option,
      label: option.name,
    }));
    return (
      <ReactSelect
        name={field.name}
        onChange={(selectedOption) => {
          // Selected option can be 1. option 2. multiselect options or 3. null because
          // if nothing is selected ReactSelect returns an empty string
          let value: Entity | Entity[] | null = selectedOption ?? null;
          if (Array.isArray(selectedOption)) {
            value = selectedOption.map((option) => option.value);
          } else if (
            // Be absolutely sure that the option is an object with a value property
            selectedOption &&
            typeof selectedOption === 'object' &&
            'value' in selectedOption
          ) {
            value = selectedOption.value;
          }
          // Should not setEntity directly (because handleChange exists)
          // but I don't have enough time :'(
          setEntity((prev) => ({
            ...prev,
            [field.name]: value,
          }));
        }}
        defaultValue={
          field.type === 'dropdown'
            ? // If it's a dropdown, get the option with the same id as the field object's id
              options?.find(
                (option) => option.value.id === entity[field.name]?.id
              )
            : // Else, get the options with the same id as the field object's id
              options?.filter((option) =>
                entity[field.name]
                  ?.map((val: T) => val.id)
                  .includes(option.value.id)
              )
        }
        options={options || []}
        isClearable={!field.required}
        isMulti={field.type === 'multiselect'}
        placeholder={`Select ${field.label}...`}
        styles={{
          // Black theme
          control: (provided) => ({
            ...provided,
            backgroundColor: 'var(--color-base-100)',
            borderColor: '#444',
            color: '#fff',
          }),
          menu: (provided) => ({
            ...provided,
            backgroundColor: '#222',
            color: '#fff',
          }),
          singleValue: (provided) => ({
            ...provided,
            color: '#fff',
          }),
          option: (provided, state) => ({
            ...provided,
            backgroundColor: state.isSelected
              ? '#444'
              : state.isFocused
              ? '#333'
              : '#222',
            color: '#fff',
          }),
          input: (provided) => ({
            ...provided,
            color: '#fff',
          }),
          placeholder: (provided) => ({
            ...provided,
            color: '#aaa',
          }),
          multiValue: (provided) => ({
            ...provided,
            backgroundColor: '#444',
            color: '#fff',
          }),
          multiValueLabel: (provided) => ({
            ...provided,
            color: '#fff',
          }),
        }}
      />
    );
  };
  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <h2 className="text-xl font-bold mb-4">
        {isEdit ? `Edit ${config.entityName}` : `Add ${config.entityName}`}
      </h2>
      {config.fields.map((field) => (
        <div key={field.name as string}>
          <label key={field.name} className="label">
            <span className="label-text">{field.label}</span>
          </label>
          {field.type === 'textarea' ? (
            <textarea
              name={field.name}
              value={entity[field.name] || ''}
              onChange={handleChange}
              placeholder={field.label}
              className="textarea textarea-bordered w-full"
              required={field.required}
            />
          ) : field.type === 'dropdown' || field.type === 'multiselect' ? (
            selectField(field)
          ) : (
            <input
              name={field.name}
              type={field.type}
              value={entity[field.name] || ''}
              onChange={handleChange}
              placeholder={field.label}
              className="input input-bordered w-full"
              required={field.required}
            />
          )}
        </div>
      ))}
      <button type="submit" className="btn btn-primary" disabled={loading}>
        {isEdit ? 'Update' : 'Create'}
      </button>
    </form>
  );
};

export const GenericCRUD = <T extends Entity>({
  config,
}: {
  config: CRUDConfig<T>;
}) => {
  const [editingId, setEditingId] = useState<number | undefined>(undefined);
  const [showForm, setShowForm] = useState(false);
  const [refreshKey, setRefreshKey] = useState(0);

  const handleEdit = (id: number) => {
    setEditingId(id);
    setShowForm(true);
  };

  const handleDelete = async (id: number) => {
    await config.delete(id);
    setRefreshKey((k) => k + 1);
  };

  const handleAdd = () => {
    setEditingId(undefined);
    setShowForm(true);
  };

  const handleSuccess = () => {
    setShowForm(false);
    setRefreshKey((k) => k + 1);
  };

  return (
    <div>
      {!showForm && (
        <>
          <div className="flex w-full items-center justify-between mb-2">
            <h2 className="text-2xl">{config.entityName} List</h2>
            {config.canCreate !== false && (
              <button className="btn btn-sm btn-success" onClick={handleAdd}>
                <FaPlus />
                Add {config.entityName}
              </button>
            )}
          </div>
          <GenericList
            key={refreshKey}
            config={config}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />
        </>
      )}
      {showForm && (
        <GenericForm
          config={config}
          entityId={editingId}
          onSuccess={handleSuccess}
        />
      )}
    </div>
  );
};
