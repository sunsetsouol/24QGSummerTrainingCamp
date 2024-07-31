import React, { useRef, useEffect, useState } from 'react'
import { sendVerificationCode, register, homePageUserInfo } from "../../api"
import { Input } from 'antd'
import { useNavigate } from 'react-router-dom'

export default function Register({ alert, setAlert }) {

    const [seconds, setSeconds] = useState(60);
    const [emailStatus, setEmailStatus] = useState('');
    const [disabled, setDisabled] = useState(false);
    let [sendyzem, setsendyzem] = useState(0)
    const firstPasswordRef = useRef()
    const secondPasswordRef = useRef()
    const emailRef = useRef()
    const agreeRef = useRef()
    const yemRef = useRef()
    const native = useNavigate()

    const setStatus = (setter, status, duration = 3000) => {
        setter(status);
        setTimeout(() => {
            setter('');
        }, duration);
    };

    //定时器提示
    const setAlertTimeout = (setter, alert, duration = 3000, type = 0) => {
        if (type === 0) {
            setter(alert)
            setTimeout(() => {
                setter({ message: '', type: "" })
            }, duration)
        }
        else {
            setter(alert)
            setTimeout(() => {
                setter({ message: '', type: "" })
                native('./header/home')
            }, duration)

        }
    }

    const startCountdown = async () => {
        setsendyzem(1)
        const email = emailRef.current.input.value;
        if (!email) {
            setStatus(setEmailStatus, 'warning');
            setAlertTimeout(setAlert, { message: 'Please enter your email.', type: 'error' });
            return;
        }
        setDisabled(true);
        let interval = setInterval(() => {
            setSeconds((prevSeconds) => prevSeconds - 1);
        }, 1000);

        setTimeout(() => {
            clearInterval(interval);
            setSeconds(60);
            setDisabled(false);
        }, 60000);

        try {
            const response = await sendVerificationCode(email);
            if (response.code == 1) {
                localStorage.setItem('token', response.data.token)
                localStorage.setItem('userIdSf', response.data.userId)
                localStorage.setItem('emailSf', email)
                async function receiveInformation() {
                    try {
                        const response = await homePageUserInfo(localStorage.getItem('userIdSf'))
                        localStorage.setItem('userName', response.data.username)
                        localStorage.setItem('userImage', response.data.image)
                        if (response.code == 1)
                            setAlertTimeout(setAlert, { message: '登录成功', type: 'success' }, 1500, 1);
                    } catch (error) {
                        console.error('Error sending verification code:', error);
                    }

                }
                receiveInformation()

            }
            else {
                setAlertTimeout(setAlert, { message: response.msg, type: 'error' });
            }
        } catch (error) {
            console.error('Error fetching models:', error);
        }
    }



    useEffect(() => {
        agreeRef.current.checked = true
    }, [])

    const registerClick = async () => {
        const email = emailRef.current.input.value;
        const firstPassword = firstPasswordRef.current.input.value;
        const secondPassword = secondPasswordRef.current.input.value;
        const yem = yemRef.current.input.value;
        const agree = agreeRef.current.checked

        if (!email) {
            setAlertTimeout(setAlert, { message: '请输入邮箱', type: 'error' });
            return;
        }
        if (!firstPassword) {
            setAlertTimeout(setAlert, { message: '请输入密码', type: 'error' });
            return;
        }
        if (!secondPassword) {
            setAlertTimeout(setAlert, { message: '请确认密码', type: 'error' });
            return;
        }
        if (firstPassword !== secondPassword) {
            setAlertTimeout(setAlert, { message: '密码不匹配', type: 'error' });
            return;
        }
        if (!sendyzem) {
            setAlertTimeout(setAlert, { message: '还未发送验证码', type: 'error' });
            return;
        }
        if (!agree) {
            setAlertTimeout(setAlert, { message: '请同意相关条款政策', type: 'error' });
            return;
        }
        try {
            const response = await register(email, yem, firstPassword);
            if (response.code === 1) {
                setAlertTimeout(setAlert, { message: '注册成功，将跳转', type: 'success' }, 1000, 1);
                localStorage.setItem('token', response.data.token)
                localStorage.setItem('userIdSf', response.data.userId)
                localStorage.setItem('emailSf', email)
            }
            else {
                setAlertTimeout(setAlert, { message: response.msg, type: 'error' });
            }
        } catch (error) {
            console.error('Error fetching models:', error);
        }
    }







    return (
        <div>
            <div className="register_center_card_bottom">
                <div><Input placeholder="输入你的邮箱" ref={emailRef} /></div>
                <div><Input placeholder="输入你的密码" type="password" ref={firstPasswordRef} /></div>
                <div><Input placeholder="确认密码" type="password" ref={secondPasswordRef} /></div>
                <div><Input placeholder="验证码" type="text" ref={yemRef} /><button
                    onClick={startCountdown}
                    disabled={disabled}>  {" "}
                    {disabled ? `重新发送(${seconds}s)` : "发送验证码"}</button></div>
            </div>
            <div className="login_center_card_bottom_bottom">
                <div><button onClick={registerClick}>注册</button></div>
                <div><div><input type="checkbox" ref={agreeRef} />已同意<a href="#">《服务条款》</a>与<a href="#">《隐私政策》</a></div>  </div>
            </div>
        </div>

    )
}
