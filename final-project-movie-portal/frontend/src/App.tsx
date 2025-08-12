import {
  createBrowserRouter,
  createRoutesFromElements,
  Outlet,
  Route,
  RouterProvider,
} from 'react-router-dom';
import './App.css';
import Login from './lib/routes/Login';
import { ProtectedRoute } from './lib/ProtectedRoute';
import Home from './lib/routes/user/Home';
import Header from './lib/Header';
import Movie from './lib/routes/user/Movie';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route path="login" element={<Login />} />
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
      </Route>
    </Route>
  )
);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
