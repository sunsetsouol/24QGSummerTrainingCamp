import React, { useState } from 'react';
import styles from './shop.module.css'
import littleImage1 from '../../assets/images/9e748038ad0f0b055344736f61190665.png';
import { useNavigate } from 'react-router-dom';

const Count = (goToDetails) => {
    const [data, setData] = useState([
        {
            image: littleImage1,
            titll: '心动小镇',
            time: '优惠时间：8.1-8.8',
            count: '折扣：80%'
        },
        {
            image: littleImage1,
            titll: '心梗小镇',
            time: '优惠时间：8.1-8.8',
            count: '折扣：80%'
        },
        {
            image: littleImage1,
            titll: '心惊小镇',
            time: '优惠时间：8.1-8.8',
            count: '折扣：80%'
        },
    ])

    const clickImg = (data) => {
        goToDetails(data)
    }


    return (
        <ul className={styles.countUl} >
            {
                data.map((item, index) => {
                    return (
                        <li>
                            <div><img src={item.image} /></div>
                            <div>
                                <div>
                                    <h2>{item.titll}</h2>
                                    <p>{item.time}</p>
                                    <p style={{ color: '#530095', fontSize: '16px' }}><div>{item.count}</div></p>
                                </div>
                            </div>
                        </li>
                    )
                })
            }



        </ul>
    );
}

export default Count;
