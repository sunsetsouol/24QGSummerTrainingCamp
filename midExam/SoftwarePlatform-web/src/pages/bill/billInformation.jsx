import React from 'react';
import styles from './bill.module.css'
import { Card } from 'antd';

const billInformation = ({ shoppingOrder }) => {


    return (
        <Card
            style={{
                width: '1118px',
                border:'2px solid #a7a7a7'

            }} className={styles.billInformation}
            bordered
        >
            <div className={styles.billInformationDiv}>
                <ul>
                    <li>
                        <div>产品信息</div>
                        {
                            shoppingOrder.map((item) => {
                                return (<div>{item.softwareName}</div>)
                            })
                        }




                    </li>
                    <li>
                        <div>起始时间</div>
                        {
                            shoppingOrder.map((item) => {
                                return (<div>{item.createTime}</div>)
                            })
                        }

                    </li>
                    <li>
                        <div>商品价格</div>
                        {
                            shoppingOrder.map((item) => {
                                return (<div>{item.price}</div>)
                            })
                        }
                    </li>
                    <li>
                        <div>数量</div>
                        {
                            shoppingOrder.map((item) => {
                                return (<div>1</div>)
                            })
                        }
                    </li>
                    <li>
                        <div>小计</div>

                        {
                            shoppingOrder.map((item) => {
                                return (<div>{item.price}</div>)
                            })
                        }
                    </li>
                </ul>
                <ul>
                    <li>总计</li>
                    <li>{shoppingOrder.reduce((total, item) => total + item.price, 0)}</li>
                </ul>
            </div>
        </Card>
    );
}

export default billInformation;
