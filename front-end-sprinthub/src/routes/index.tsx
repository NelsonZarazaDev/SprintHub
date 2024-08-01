import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Error404 from "../view/Error404/Error404";
import Login from "../views/Login/Login";
import Home from "../views/Home/Home";
import Register from "../views/Register/Register";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
    errorElement: <Error404 />,
  },
  {
    path: "/register",
    element: <Register />,
    errorElement: <Error404 />,
  },
  {
    path: "/home",
    element: <Home />,
  },
]);

const MyRoutes = () => <RouterProvider router={router} />;

export default MyRoutes;
