import React, { useRef, useEffect } from "react";
import { Input } from "antd";
import { accountLogin, homePageUserInfo } from "../../api";
import { useNavigate } from "react-router-dom";
import { useAdmin } from "../../utils/context";
export default function PasswordLogin({ alert, setAlert }) {
  const passwordRef = useRef();
  const emailRef = useRef();
  const rembermeRef = useRef();
  const agreeRef = useRef();
  const native = useNavigate();
  const { isAdmin, setIsAdmin } = useAdmin();
  //定时器提示
  const setAlertTimeout = (setter, alert, duration = 3000, type = 0) => {
    if (type == 0) {
      setter(alert);
      setTimeout(() => {
        setter({ message: "", type: "" });
      }, duration);
    } else {
      setter(alert);
      setTimeout(() => {
        let loginStage = 1;
        setter({ message: "", type: "" });
        native("./header/home");
      }, duration);
    }
  };

  //登录按钮
  const login_In = async () => {
    const password = passwordRef.current.input.value;
    const email = emailRef.current.input.value;
    const agree = agreeRef.current.checked;
    if (!email) {
      setAlertTimeout(setAlert, { message: "请输入邮箱", type: "error" });
      return;
    }
    if (!password) {
      setAlertTimeout(setAlert, { message: "请输入密码", type: "error" });
      return;
    }
    if (!agree) {
      setAlertTimeout(setAlert, {
        message: "请同意相关条款政策",
        type: "error",
      });
      return;
    }
    try {
      let response;
      if (rembermeRef.current.checked) {
        response = await accountLogin(email, password, 0);
        setIsAdmin(true);
      } else response = await accountLogin(email, password, 1);
      console.log(response);
      if (response.code == 1) {
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("userIdSf", response.data.userId);
        localStorage.setItem("emailSf", email);
        async function receiveInformation() {
          try {
            // const response = await homePageUserInfo(
            //   localStorage.getItem("userIdSf")
            // );
            // localStorage.setItem("userName", response.data.username);
            // localStorage.setItem("userImage", response.data.image);
            if (response.code == 1)
              setAlertTimeout(
                setAlert,
                { message: "登录成功", type: "success" },
                1500,
                1
              );
          } catch (error) {
            console.error("Error sending verification code:", error);
          }
        }
        receiveInformation();
      } else {
        setAlertTimeout(setAlert, { message: response.msg, type: "error" });
      }
    } catch (error) {
      console.error("Error fetching models:", error);
    }
  };

  useEffect(() => {
    agreeRef.current.checked = true;
  }, []);

  return (
    <div>
      <div className="login_center_card_bottom">
        <h3>欢迎使用工软软件管理平台</h3>
        <div>
          <Input placeholder="输入你的邮箱" ref={emailRef} />
        </div>
        <div>
          {" "}
          <Input placeholder="输入你的密码" ref={passwordRef} type="password" />
        </div>
        <div className="remberMeBigDiv">
          <div>
            <input type="checkbox" ref={rembermeRef} />
            管理员入口
          </div>
        </div>
      </div>
      <div className="login_center_card_bottom_bottom">
        <div>
          <button onClick={login_In}>登录</button>
        </div>
        <div>
          <div>
            <input type="checkbox" ref={agreeRef} />
            已同意<a>《服务条款》</a>与<a>《隐私政策》</a>
          </div>{" "}
        </div>
      </div>
    </div>
  );
}
