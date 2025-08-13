import { FaUser } from 'react-icons/fa';
import { FiLogOut, FiSettings } from 'react-icons/fi';
import { MdAdminPanelSettings, MdLocalMovies } from 'react-icons/md';
import { Link, useMatch, useNavigate } from 'react-router-dom';
import { useUserStore } from '../state/user';
import { api } from '../api/index';
import React from 'react';
import type { IconType } from 'react-icons';

const NavbarLink = ({
  to,
  title,
  icon,
}: {
  to: string;
  title: string;
  icon: IconType;
}) => {
  const isActive = useMatch({ path: to, end: true });
  return (
    <li className="mb-1">
      <Link
        to={to}
        className={isActive ? 'bg-base-100 text-base-content' : ''}
        onClick={() => {
          if (document.activeElement instanceof HTMLElement) {
            document.activeElement.blur();
          }
        }}
      >
        {React.createElement(icon)}
        {title}
      </Link>
    </li>
  );
};

export const Header = () => {
  const navigate = useNavigate();
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
            <NavbarLink
              to={'/user/profile/' + useUserStore.getState().user?.username}
              title="Profile"
              icon={FaUser}
            />
            {useUserStore
              .getState()
              .user?.roles?.some((role) => role.name === 'ADMIN') ? (
              <NavbarLink
                to="/admin"
                title="Admin Panel"
                icon={MdAdminPanelSettings}
              />
            ) : null}
            <NavbarLink
              to={'/user/settings'}
              title="Settings"
              icon={FiSettings}
            />
            <li>
              <a
                onClick={() => {
                  api.logout();
                  navigate('/login');
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
};
