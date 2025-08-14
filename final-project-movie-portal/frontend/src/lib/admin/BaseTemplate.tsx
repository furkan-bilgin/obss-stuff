import type { PropsWithChildren } from 'react';
import { useMatch } from 'react-router-dom';
import { Header } from '../header/Header';
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
import { FaImdb } from 'react-icons/fa';
import { Link } from '../Link';

const NavbarLink = ({
  to,
  icon,
  children,
}: {
  to: string;
  icon: IconType;
  children: string;
}) => {
  const isActive = useMatch({ path: to, end: true });
  return (
    <li className="mb-1">
      <Link to={to} className={isActive ? 'bg-base-100 text-base-content' : ''}>
        {React.createElement(icon)}
        {children}
      </Link>
    </li>
  );
};

export const AdminBaseTemplate = (props: PropsWithChildren) => {
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
      <NavbarLink to="/admin/fetch-imdb" icon={FaImdb}>
        Fetch IMDB Titles
      </NavbarLink>
      <div className="divider"></div>
      <NavbarLink to="/user" icon={BiLeftArrowAlt}>
        Back to Site
      </NavbarLink>
    </>
  );

  return (
    <div className="drawer drawer-open">
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
