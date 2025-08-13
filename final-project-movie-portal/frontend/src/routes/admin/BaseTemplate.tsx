import type { PropsWithChildren } from 'react';
import { NavLink } from 'react-router-dom';
import { Header } from '../../lib/Header';
import type { IconType } from 'react-icons';
import React from 'react';
import {
  BiCategory,
  BiHome,
  BiLeftArrowAlt,
  BiMovie,
  BiUser,
} from 'react-icons/bi';
import { TbChairDirector } from 'react-icons/tb';

const NavbarLink = ({
  to,
  icon,
  children,
}: {
  to: string;
  icon: IconType;
  children: string;
}) => {
  return (
    <li className="mb-1">
      <NavLink
        to={to}
        className={({ isActive }) =>
          isActive ? 'bg-base-100 text-base-content' : ''
        }
        end={true}
      >
        {React.createElement(icon)}
        {children}
      </NavLink>
    </li>
  );
};

const AdminBaseTemplate = (props: PropsWithChildren) => {
  const sidebarContent = (
    <>
      <li className="menu-title px-4">
        <span>Admin Panel</span>
      </li>
      <NavbarLink to="/admin" icon={BiHome}>
        Dashboard
      </NavbarLink>
      <NavbarLink to="/admin/users" icon={BiUser}>
        Users
      </NavbarLink>
      <NavbarLink to="/admin/movies" icon={BiMovie}>
        Movies
      </NavbarLink>
      <NavbarLink to="/admin/directors" icon={TbChairDirector}>
        Directors
      </NavbarLink>
      <NavbarLink to="/admin/categories" icon={BiCategory}>
        Categories
      </NavbarLink>
      <div className="divider"></div>
      <NavbarLink to="/user" icon={BiLeftArrowAlt}>
        Back to Site
      </NavbarLink>
    </>
  );

  return (
    <div
      className={'drawer ' + (window.innerWidth >= 1024 ? 'drawer-open' : '')}
    >
      <input id="admin-drawer" type="checkbox" className="drawer-toggle" />
      <div className="drawer-content flex flex-col">
        {/* Navbar */}
        <Header />
        <div className="p-4">{props.children}</div>
      </div>
      <div className="drawer-side">
        <label
          htmlFor="admin-drawer"
          aria-label="close sidebar"
          className="drawer-overlay"
        ></label>
        <ul className="menu bg-base-300 min-h-full w-80 p-4">
          {sidebarContent}
        </ul>
      </div>
    </div>
  );
};

export default AdminBaseTemplate;
