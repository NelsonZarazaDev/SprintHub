import { useEffect } from "react";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

interface AlertsProps {
  message: string;
  type: string;
}

const customId = "custom-id-yes";


export default function Alerts({ message, type }: AlertsProps) {
  useEffect(() => {
    switch (type) {
      case "success":
        toast.success(message, {
      toastId: customId
    });
        break;
      case "error":
        toast.error(message, {
      toastId: customId
    });
        break;
      case "info":
        toast.info(message, {
      toastId: customId
    });
        break;
      case "warning":
        toast.warn(message, {
      toastId: customId
    });
        break;
      default:
        toast(message, {
      toastId: customId
    });
        break;
    }
  }, [message, type]);

  return null;
}
