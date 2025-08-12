import {
  createBrowserRouter,
  createRoutesFromElements,
  Outlet,
  Route,
  RouterProvider,
} from 'react-router-dom';
import './App.css';
import Login from './routes/Login';
import { ProtectedRoute } from './lib/ProtectedRoute';
import Header from './lib/Header';
import Movie from './routes/user/Movie';
import { Home } from './routes/user/Home';

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
