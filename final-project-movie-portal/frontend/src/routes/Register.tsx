import { useState } from 'react';
import { api } from '../api';
import { Link, useNavigate } from 'react-router-dom';
import { ErrorMessage } from '../lib/ErrorMessage';
import { getAPIError } from '../api/utils';

export const Register = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [error, setError] = useState<Error | null>(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      setLoading(true);
      await api.authService.register(username, password, email);
      navigate('/user');
    } catch (err) {
      if (err instanceof Error) {
        setError(
          getAPIError(err, {
            unauthorized: 'Invalid username or password.',
            conflict: 'Username or Email already exists.',
          })
        );
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-base-200">
      <div className="w-full max-w-sm p-8 shadow-lg rounded-lg bg-base-100">
        <h2 className="text-2xl font-bold mb-6 text-center">Register</h2>
        {error && <ErrorMessage error={error} />}
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="label">
              <span className="label-text">Username</span>
            </label>
            <input
              type="text"
              className="input input-bordered w-full"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div>
            <label className="label">
              <span className="label-text">Email</span>
            </label>
            <input
              type="email"
              className="input input-bordered w-full"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div>
            <label className="label">
              <span className="label-text">Password</span>
            </label>
            <input
              type="password"
              className="input input-bordered w-full"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button
            type="submit"
            className="btn btn-primary w-full"
            disabled={loading}
          >
            {loading ? (
              <>
                <span className="loading loading-spinner"></span>
                Registering...
              </>
            ) : (
              'Register'
            )}
          </button>
          <div className="flex w-full justify-center">
            <span className="text-sm text-gray-500">
              or{' '}
              <Link to="/login" className="underline text-primary">
                Login
              </Link>
            </span>
          </div>
        </form>
      </div>
    </div>
  );
};
