import React, { useState } from "react";
import logo from "../../assets/Favicon.png";
import { Input } from "@nextui-org/react";
import { GoEye, GoEyeClosed } from "react-icons/go";
import { Button } from "@nextui-org/react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import Alerts from "../../components/Alerts/Alerts";

interface LoginState {
  email: string;
  password: string;
}

interface AlertInfo {
  message: string;
  type: string;
}

export default function Register() {
  const navigate = useNavigate();
  const [alertInfoList, setAlertInfoList] = useState<AlertInfo[]>([]);
  const [login, setLogin] = useState<LoginState>({
    email: "",
    password: "",
  });

  const { email, password } = login;

  const onInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setLogin({ ...login, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const url = "http://localhost:8080/auth/login";
      const response = await axios.post(url, login);
      localStorage.setItem("token", response.data.token);
      addAlert("Sesion iniciada", "success");
      setTimeout(() => {
        navigate("/home");
      }, 1000);
    } catch (error) {
      if (axios.isAxiosError(error)) {
        const data = error.response?.data;
        const dataArray = Object.values(data);
        addAlert(dataArray[0] as string, "error");
      } else {
        addAlert("An unexpected error occurred", "error");
      }
    }
  };

  const [isVisible, setIsVisible] = useState(false);
  const toggleVisibility = () => setIsVisible(!isVisible);

  const addAlert = (message: string, type: string) => {
    const newAlertInfoList = [...alertInfoList, { message, type }];
    setAlertInfoList(newAlertInfoList);
  };

  return (
    <div className="w-full h-screen flex flex-col justify-center items-center">
      <img className="w-20 m-9 z-10" src={logo} alt="logo" />
      <form
        className="border-2 border-solid border-containerEdges w-80 md:w-96 lg:w-1/4 rounded-xl space-y-7 p-10 -translate-y-16"
        onSubmit={onSubmit}
      >
        <div className="space-y-0">
          <label className="font-bold text-lg" htmlFor="email">
            Email:
          </label>
          <Input
            type="email"
            id="email"
            name="email"
            value={email}
            onChange={onInputChange}
            className="text-mainText border-2 border-solid border-containerEdges rounded-xl w-full mt-12 text-lg"
          />
        </div>

        <div className="space-y-0">
          <label className="font-bold text-lg" htmlFor="password">
            Contraseña:
          </label>
          <Input
            id="password"
            name="password"
            value={password}
            onChange={onInputChange}
            endContent={
              <button
                type="button"
                onClick={toggleVisibility}
                aria-label="toggle password visibility"
              >
                {isVisible ? (
                  <GoEyeClosed className="text-2xl text-default-400 pointer-events-none" />
                ) : (
                  <GoEye className="text-2xl text-default-400 pointer-events-none" />
                )}
              </button>
            }
            type={isVisible ? "text" : "password"}
            className="text-mainText border-2 border-solid border-containerEdges rounded-xl w-full"
          />
        </div>
        <div className="w-full flex flex-col items-center space-y-3">
          <Button
            type="submit"
            className="text-mainText bg-primaryButton font-bold w-44 h-12 text-lg"
            radius="lg"
          >
            Enviar
          </Button>
          <p>¿No tienes una cuenta? <Link to={`register`}><b>Registrate</b></Link></p>
        </div>
      </form>
      {alertInfoList.map((alert, index) => (
        <Alerts key={index} message={alert.message} type={alert.type} />
      ))}
    </div>
  );
}
