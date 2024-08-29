import React, { useState, useEffect, useRef } from "react";
import "./login.css";
import Center from "./center";
import { Alert } from "antd";

export default function Index() {
  const [isLogin, setIsLogin] = useState(true);
  const [alert, setAlert] = useState({ message: "", type: "" });
  const mainRef = useRef();

  return (
    <div className="main" ref={mainRef}>
      {alert.message && (
        <Alert
          message={alert.message}
          type={alert.type}
          className="alert_message"
        />
      )}
      <Center
        isLogin={isLogin}
        setIsLogin={setIsLogin}
        alert={alert}
        setAlert={setAlert}
      />
    </div>
  );
}
