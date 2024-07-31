import React, { useState } from 'react';
import styles from './shop.module.css'
import littleImage1 from '../../assets/images/605b0dc354d5ccb7d3d63eba17900968.png';
import { useNavigate } from 'react-router-dom';

const EveryoneDownLoad = (goToDetails) => {

    const [data, setData] = useState([
        {
            image: littleImage1,
            titll: '出发吧麦芬',
            tag1: '策略',
            tag2: '养成',
            tag3: '战斗',
            tag4: '萌系',
            description: '治愈系组队冒险放置手游'

        }, {
            image: littleImage1,
            titll: '出发吧麦芬',
            tag1: '策略',
            tag2: '养成',
            tag3: '战斗',
            tag4: '萌系',
            description: '治愈系组队冒险放置手游'

        }, {
            image: littleImage1,
            titll: '出发吧麦芬',
            tag1: '策略',
            tag2: '养成',
            tag3: '战斗',
            tag4: '萌系',
            description: '治愈系组队冒险放置手游'

        }, {
            image: littleImage1,
            titll: '出发吧麦芬',
            tag1: '策略',
            tag2: '养成',
            tag3: '战斗',
            tag4: '萌系',
            description: '治愈系组队冒险放置手游'

        }
    ])
    const navigate = useNavigate()
    const handleClick = () => {
        // console.log(111);

        navigate('/header/verifybill')
        window.scrollTo(0, 0)
    }

    return (
        <ul className={styles.everyoneDownLoad} >
            {data.map((item, index) => {
                return (
                    <li>
                        <div><img src={littleImage1} /></div>
                        <div>
                            <h3>{item.titll}</h3>
                            <p>{item.description}</p>
                            <ul>
                                <li>{item.tag1}</li>
                                <li>{item.tag2}</li>
                                <li>{item.tag3}</li>
                                <li>{item.tag4}</li>
                            </ul>
                        </div>
                    </li>
                )
            })}
        </ul>
    );
}

export default EveryoneDownLoad;
