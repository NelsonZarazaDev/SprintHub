import React from "react";
import { useRouteError } from "react-router-dom";

export default function Error404() {
  const error = useRouteError();
  return (
    <div>
      <div className="flex justify-center text-[20rem] font-bold">
        <div>4</div>
        <div>🌎</div>
        <div>4</div>
      </div>
      <div className="w-full text-2xl font-bold flex flex-col items-center ">
        <h3>Ops!</h3>
        <p>No hemos encontrado la ruta que buscas</p>
        <p>{error.status && error.data}</p>
      </div>
    </div>
  );
}
