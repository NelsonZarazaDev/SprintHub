import React, { useState } from "react";
import logo from "../../assets/Favicon.png";
import { Input, Button } from "@nextui-org/react";
import { GoEye, GoEyeClosed } from "react-icons/go";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Alerts from "../../components/Alerts/Alerts";

interface RegisterState {
  firstnameUser: string;
  lastNameUser: string;
  dateBirthUser: string;
  emailUser: string;
  passwordUser: string;
  verifyPassword: string;
}

interface AlertInfo {
  message: string;
  type: string;
}

export default function Register() {
  const navigate = useNavigate();
  const [alertInfoList, setAlertInfoList] = useState<AlertInfo[]>([]);
  const [register, setRegister] = useState<RegisterState>({
    firstnameUser: "",
    lastNameUser: "",
    dateBirthUser: "",
    emailUser: "",
    passwordUser: "",
    verifyPassword:""
  });

  const {
    firstnameUser,
    lastNameUser,
    dateBirthUser,
    emailUser,
    passwordUser,
    verifyPassword
  } = register;

  const onInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setRegister({ ...register, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const url = "http://localhost:8080/auth/register";
      const response = await axios.post(url, register);
      localStorage.setItem("token", response.data.token);
      addAlert("Usuario registrado con exito", "success");
      setTimeout(() => {
        navigate("/");
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
        className="border-2 border-solid border-containerEdges w-80 md:w-3/4 lg:w-2/4 rounded-xl space-y-7 p-10 -translate-y-16"
        onSubmit={onSubmit}
      >
        <div className="sm:grid grid-cols-2 sm:gap-4">
          <div className="space-y-0">
            <label className="font-bold text-lg" htmlFor="firstnameUser">
              Nombres:
            </label>
            <Input
              type="text"
              id="firstnameUser"
              name="firstnameUser"
              value={firstnameUser}
              onChange={onInputChange}
              className="text-mainText border-2 border-solid border-containerEdges rounded-xl w-full mt-12 text-lg"
            />
          </div>

          <div className="space-y-0">
            <label className="font-bold text-lg" htmlFor="lastNameUser">
              Apellidos:
            </label>
            <Input
              type="text"
              id="lastNameUser"
              name="lastNameUser"
              value={lastNameUser}
              onChange={onInputChange}
              className="text-mainText border-2 border-solid border-containerEdges rounded-xl w-full mt-12 text-lg"
            />
          </div>

          <div className="space-y-0">
            <label className="font-bold text-lg" htmlFor="dateBirthUser">
              Fecha de nacimiento:
            </label>
            <Input
              type="date"
              id="dateBirthUser"
              name="dateBirthUser"
              value={dateBirthUser}
              onChange={onInputChange}
              className="text-mainText border-2 border-solid border-containerEdges rounded-xl w-full mt-12 text-lg"
            />
          </div>

          <div className="space-y-0">
            <label className="font-bold text-lg" htmlFor="emailUser">
              Email:
            </label>
            <Input
              type="email"
              id="emailUser"
              name="emailUser"
              value={emailUser}
              onChange={onInputChange}
              className="text-mainText border-2 border-solid border-containerEdges rounded-xl w-full mt-12 text-lg"
            />
          </div>

          <div className="space-y-0">
            <label className="font-bold text-lg" htmlFor="passwordUser">
              Contraseña:
            </label>
            <Input
              id="passwordUser"
              name="passwordUser"
              value={passwordUser}
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

          <div className="space-y-0">
            <label className="font-bold text-lg" htmlFor="password">
              Verificar contraseña:
            </label>
            <Input
             id="verifyPassword"
             name="verifyPassword"
             value={verifyPassword}
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
        </div>

        <div className="w-full flex flex-col items-center space-y-3">
          <Button
            type="submit"
            className="text-mainText bg-primaryButton font-bold w-44 h-12 text-lg"
            radius="lg"
          >
            Enviar
          </Button>
        </div>
      </form>
      {alertInfoList.map((alert, index) => (
        <Alerts key={index} message={alert.message} type={alert.type} />
      ))}
    </div>
  );
}
