import React from 'react'
import PasswordLogin from "./passwordLogin";
import Register from "./register";
import { Card, Input } from 'antd'

export default function center({ isLogin, setIsLogin, alert, setAlert }) {
    return (
        <div className="login_center">
            <div className="login_center_card">
                <Card style={{ width: '520px', height: '570px', padding: '0px', margin: '0px' }} className="custom-card">
                    <div className="select_login_register">
                        <h3 className={isLogin ? "select_login_register_left select_login_register_active" : "select_login_register_left"} onClick={() => setIsLogin(true)}>登录</h3>
                        <h3 className={isLogin ? "select_login_register_right " : "select_login_register_right select_login_register_active"} onClick={() => setIsLogin(false)}>注册</h3>
                    </div>
                    {isLogin ? <PasswordLogin alert={alert} setAlert={setAlert} /> : <Register alert={alert} setAlert={setAlert} />}
                </Card>
            </div>
        </div>
    )
}
