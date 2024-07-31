import React, { useEffect, useState, useRef } from 'react';
import styles from './bill.module.css'
import { Card } from 'antd';
import { NavLink } from 'react-router-dom'
import { Modal } from 'antd';
import { getFingerprint } from "../../api"


const BuyAccountInformation = ({ onSelectFingerprint }) => {


    const buttonRef = useRef()
    const [isModalOpen, setIsModalOpen] = useState(false);
    const showModal = () => {
        setIsModalOpen(true);
    };
    const handleOk = () => {
        setIsModalOpen(false);
    };
    const handleCancel = () => {
        setIsModalOpen(false);
    };
    const returnName = '管理我的指纹>>'

    const [Fingerprint, setFingerprint] = useState('')
    const [showName, setShowName] = useState('选择你的指纹信息')

    const [data, setData] = useState([
    ])

    useEffect(() => {
        async function receiveInformation() {
            try {
                const response = await getFingerprint(localStorage.getItem('userIdSf'))
                setData(response.data)
            } catch (error) {
                console.error('Error sending verification code:', error);
            }

        }
        receiveInformation()
    }, [])

    //用于展示指纹信息
    const show = (name, id) => {
        setFingerprint(id)
        setShowName(name)
        onSelectFingerprint({
            name,
            id
        });
        handleCancel()
    }




    return (
        <div className={styles.buyAccountInformation}>
            <div>
                <ul>
                    <li>用户名</li>
                    <li>电子邮件地址</li>
                    <li>授权账户ID</li>
                    <li>指纹信息</li>
                </ul>
                <ul>
                    <li>用户名</li>
                    <li>1231234567@xxx.com</li>
                    <li>20002951</li>
                    <li>1</li>
                    <button onClick={() => showModal()} ref={buttonRef} value={Fingerprint}>{showName}</button>
                </ul>
            </div>
            <div>
                <Card
                    style={{
                        width: 200,
                        height: 200, border: '2px solid #a7a7a7'
                    }}
                    bordered
                >
                    <span className={styles.rihgtCardWord} >当您完成此笔交易后，款项会自您的支付方式中扣除，且您将收到一封电子邮件确认您的购买。</span>
                </Card>

            </div>
            <Modal open={isModalOpen} onOk={handleOk} onCancel={handleCancel}
                style={{
                    top: 300,
                }}
                width={254}
            >
                {data ? (
                    <ul className={styles.ModalFirst}>
                        {data.map((item, index) => (
                            <li key={index} wareName={item.hardwareName} fingerprint={item.fingerprint} onClick={() => show(item.hardwareName, item.userFingerprintId)}>  {item.hardwareName}</li>
                        ))}
                        <NavLink to="/header/person" className={styles.link}>
                            {returnName}
                        </NavLink>
                    </ul>
                ) : (
                        <ul className={styles.ModalSecond}>
                            <h3>暂未添加指纹信息点击去</h3>
                            <NavLink to="/header/person" className={styles.link}>
                                {returnName}
                            </NavLink>
                        </ul>
                    )}

            </Modal>
        </div>
    );
}

export default BuyAccountInformation;
