import React, { useState, useEffect } from 'react';
import styles from './bill.module.css'
import BillInformation from "./billInformation"
import BuyAccountInformation from './buyAccountInformation'
import Textarea from './textarea'
import { purchaseAuth } from "../../api"
import { useNavigate } from 'react-router-dom'
import { Alert } from 'antd';
import { useOutletContext } from 'react-router-dom'

const Bill = () => {

    const [alert, setAlert] = useState({ message: '', type: '' });
    const [selectedInformation, setSelectedInformation] = useState({});
    const navigate = useNavigate()
    const handleFingerprintSelection = (infromation) => {
        setSelectedInformation(infromation);
    }

    const { shoppingOrder } = useOutletContext();

    useEffect(() => {
    }, [selectedInformation]);

    //点击购买按钮后
    const desireBuy = () => {
        if (!selectedInformation.id)
            setAlertTimeout(setAlert, { message: '请先选择硬件指纹', type: 'error' });
        else {
            const totalPrice = shoppingOrder.reduce((total, item) => total + item.price, 0)
            const softwareList = shoppingOrder.map(item => ({
                softwareId: item.softwareId,
                softwareName: item.softwareName,
                versionType: item.versionType
            }));


            async function buyAll() {
                try {
                    const response = await purchaseAuth(localStorage.getItem('userIdSf'), selectedInformation.id, totalPrice, softwareList)

                } catch (error) {
                    console.error('Error sending verification code:', error);
                }

            }
            buyAll()
            setAlertTimeout(setAlert, { message: '成功,即将返回登录界面', type: 'success' }, 3000, 1);
        }

    }
    //定时器
    const setAlertTimeout = (setter, alert, duration = 3000, type = 0) => {
        if (type == 0) {
            setter(alert)
            setTimeout(() => {
                setter({ message: '', type: "" })
            }, duration)
        }
        else {
            setter(alert)
            setTimeout(() => {
                setter({ message: '', type: "" })
                navigate('/header/shop');
            }, duration)

        }
    }


    //退回上一步
    const comeBack = () => {
        navigate('/header/subscription');
    }







    return (
        <div className={styles.Div}>
            <div className={styles.BigDiv}>
                <h1 className={styles.agreeBill} >确认订单</h1>
                <BillInformation shoppingOrder={shoppingOrder} />
                <h1 className={styles.buyaccout} >购买账号</h1>
                <BuyAccountInformation onSelectFingerprint={handleFingerprintSelection} />
                <h1 className={styles.assess}>评价</h1>
                <Textarea />
                <h2 className={styles.cost}>订单总价：1400CNY</h2>
                <div className={styles.buybottom}>
                    <button onClick={comeBack}>返回</button>
                    <button onClick={desireBuy}>确认订单并支付</button>
                </div>
            </div>
            <div className={styles.alert_message}>
                {alert.message && <Alert message={alert.message} type={alert.type} className={styles.alert_message} />}
            </div>
        </div>
    );
}

export default Bill;