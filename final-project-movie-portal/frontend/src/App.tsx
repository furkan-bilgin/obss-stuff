import {
  createBrowserRouter,
  createRoutesFromElements,
  Navigate,
  Outlet,
  Route,
  RouterProvider,
} from 'react-router-dom';
import './App.css';
import { Login } from './routes/Login';
import { ProtectedRoute } from './lib/ProtectedRoute';
import { Header } from './lib/Header';
import { Movie } from './routes/user/Movie';
import { Home } from './routes/user/Home';
import { Profile } from './routes/user/Profile';
import { AdminHome } from './routes/admin/AdminHome';
import { AdminBaseTemplate } from './lib/admin/BaseTemplate';
import { AdminMovie } from './routes/admin/AdminMovie';
import { AdminDirector } from './routes/admin/AdminDirector';
import { AdminCategory } from './routes/admin/AdminCategory';
import { AdminUser } from './routes/admin/AdminUser';
import { api } from './api';
import { AdminFetchIMDb } from './routes/admin/AdminFetchIMDb';
import { Register } from './routes/Register';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route index element={<Navigate to="/login" />} />
      <Route path="login" element={<Login />} />
      <Route path="register" element={<Register />} />
      <Route
        path="user"
        element={
          <ProtectedRoute>
            <Header />
            <div className="flex justify-center mt-4">
              <Outlet />
            </div>
          </ProtectedRoute>
        }
      >
        <Route index element={<Home />} />
        <Route path="movie/:id" element={<Movie />} />
        <Route path="profile/:username" element={<Profile />} />
      </Route>
      <Route
        path="admin"
        element={
          <ProtectedRoute>
            <AdminBaseTemplate>
              <Outlet />
            </AdminBaseTemplate>
          </ProtectedRoute>
        }
      >
        <Route index element={<AdminHome />} />
        <Route path="users" element={<AdminUser />} />
        <Route path="movies" element={<AdminMovie />} />
        <Route path="directors" element={<AdminDirector />} />
        <Route path="categories" element={<AdminCategory />} />
        <Route path="fetch-imdb" element={<AdminFetchIMDb />} />
      </Route>
    </Route>
  )
);

function App() {
  api.init();
  return <RouterProvider router={router} />;
}

export default App;
