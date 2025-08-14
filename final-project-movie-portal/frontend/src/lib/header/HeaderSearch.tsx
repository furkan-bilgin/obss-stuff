import { useEffect, useRef, useState } from 'react';
import { BiSearch } from 'react-icons/bi';
import { FaXmark } from 'react-icons/fa6';
import { useNavigate } from 'react-router-dom';

export const HeaderSearch = () => {
  const [search, setSearch] = useState('');
  const [isSearch, setIsSearch] = useState(false);
  const searchInputRef = useRef<HTMLInputElement>(null);
  const navigate = useNavigate();
  const handleSearch = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearch(e.target.value);
    setIsSearch(true);
  };
  const handleClear = () => {
    setSearch('');
    setIsSearch(false);
  };
  useEffect(() => {
    if (searchInputRef.current) {
      searchInputRef.current.focus();
    }
  }, [isSearch]);
  return (
    <>
      {isSearch && (
        <form
          onSubmit={(e) => {
            e.preventDefault();
            setIsSearch(false);
            if (!search) return;
            navigate('/user/search/' + search);
            handleClear();
          }}
          onKeyDown={(e) => {
            if (e.key === 'Escape') {
              handleClear();
            }
          }}
        >
          <input
            ref={searchInputRef}
            type="text"
            placeholder="Search for movies..."
            className="input input-bordered "
            value={search}
            onChange={handleSearch}
          />
        </form>
      )}
      {!isSearch ? (
        <button
          type="button"
          className="btn btn-ghost btn-circle text-xl"
          onClick={() => setIsSearch(true)}
        >
          <BiSearch />
        </button>
      ) : (
        <button
          type="button"
          className="btn btn-ghost btn-circle text-xl"
          onClick={handleClear}
        >
          <FaXmark />
        </button>
      )}
    </>
  );
};
