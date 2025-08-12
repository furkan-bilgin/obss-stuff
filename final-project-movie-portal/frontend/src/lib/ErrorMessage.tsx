import { BiError } from 'react-icons/bi';

export const ErrorMessage = ({ error }: { error: Error }) => {
  return (
    <div className="alert alert-error shadow-lg text-white font-bold">
      <BiError size={20} />
      <span>{error.message}</span>
    </div>
  );
};
