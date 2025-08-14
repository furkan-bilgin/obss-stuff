import { useState } from 'react';
import { useUserStore } from '../../state/user';
import { api } from '../../api';
import { ErrorMessage } from '../../lib/ErrorMessage';
import { LoadingSpinner } from '../../lib/LoadingSpinner';

export const Settings = () => {
  const user = useUserStore((state) => state.user);
  const [email, setEmail] = useState(user?.email ?? '');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<Error | null>(null);
  const [success, setSuccess] = useState<string | null>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    setSuccess(null);
    try {
      if (!user) throw new Error('User not found');
      await api.userService.updateMe({
        email: email,
        password: password,
      });
      await api.refreshUser();
      setSuccess('Settings updated successfully.');
      setPassword('');
    } catch (err) {
      if (err instanceof Error) setError(err);
    } finally {
      setLoading(false);
    }
  };

  if (!user) return <LoadingSpinner />;

  return (
    <div className="max-w-sm w-full mx-auto mt-8 card bg-base-100 shadow-xl p-6">
      <h2 className="text-2xl font-bold mb-4">Account Settings</h2>
      <form onSubmit={handleSubmit} className="flex flex-col gap-4">
        <label className="form-control">
          <span className="label-text">Email</span>
          <input
            type="email"
            className="input input-bordered w-full"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </label>
        <label className="form-control">
          <span className="label-text">New Password</span>
          <input
            type="password"
            className="input input-bordered w-full"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Leave blank to keep your current password"
            minLength={6}
          />
        </label>
        <button type="submit" className="btn btn-primary" disabled={loading}>
          {loading ? <LoadingSpinner /> : 'Update Settings'}
        </button>
        {error && <ErrorMessage error={error} />}
        {success && <div className="alert alert-success mt-2">{success}</div>}
      </form>
    </div>
  );
};
