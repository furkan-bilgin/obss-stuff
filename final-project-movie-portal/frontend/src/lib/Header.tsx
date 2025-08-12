import { FaUser } from 'react-icons/fa';
import { FiLogOut, FiSettings } from 'react-icons/fi';
import { MdLocalMovies } from 'react-icons/md';
import { logout } from '../api';
import { Link } from 'react-router-dom';

export default function Header() {
  return (
    <div className="navbar bg-base-100 shadow-sm">
      <div className="flex-1">
        <Link to="/user" className="btn btn-ghost text-xl">
          <MdLocalMovies />
          Movie Portal
        </Link>
      </div>
      <div className="flex-none">
        <div className="dropdown dropdown-end">
          <div
            tabIndex={0}
            role="button"
            className="btn btn-ghost btn-circle avatar"
          >
            <FaUser />
          </div>
          <ul
            tabIndex={0}
            className="menu menu-sm dropdown-content bg-base-100 rounded-box z-1 mt-3 w-52 p-2 shadow"
          >
            <li>
              <a>
                <FaUser />
                Profile
              </a>
            </li>
            <li>
              <a>
                <FiSettings />
                Settings
              </a>
            </li>
            <li>
              <a
                onClick={() => {
                  logout();
                  window.location.reload();
                }}
              >
                <FiLogOut />
                Logout
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
}
