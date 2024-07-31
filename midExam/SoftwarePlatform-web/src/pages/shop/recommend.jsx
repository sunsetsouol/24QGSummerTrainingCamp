import React, { useState } from 'react';
import styles from './shop.module.css'
import littleImage1 from '../../assets/images/670d8aa44292f234551dc4d837c4257a.png';
import littleImage2 from '../../assets/images/09a5bdc1face7ec001ae71105bf91809.png';
import { useNavigate } from 'react-router-dom';

const Recommend = () => {
    const [data, setData] = useState([
        {
            imageleft: littleImage1,
            imageright: littleImage2,
            titll: '元气骑士',
            tag1: '肉鸽',
            tag2: '像素',
            tag3: '俯视',
            tag4: '地牢',
            description: '这款打宝闯关两不误，休闲轻松很惬意的ARPG游戏，你一定不能错过！'
        }, {
            imageleft: littleImage1,
            imageright: littleImage2,
            titll: '元气骑士',
            tag1: '肉鸽',
            tag2: '像素',
            tag3: '俯视',
            tag4: '地牢',
            description: '这款打宝闯关两不误，休闲轻松很惬意的ARPG游戏，你一定不能错过！'
        }, {
            imageleft: littleImage1,
            imageright: littleImage2,
            titll: '元气骑士',
            tag1: '肉鸽',
            tag2: '像素',
            tag3: '俯视',
            tag4: '地牢',
            description: '这款打宝闯关两不误，休闲轻松很惬意的ARPG游戏，你一定不能错过！'
        }
    ])


    // const navigate = useNavigate()
    // const handleClick = () => {
    //     // console.log(111);

    //     navigate('/header/verifybill')
    //     window.scrollTo(0, 0)
    // }

    return (
        <ul className={styles.recommendUl} >
            {
                data.map((item, index) => {
                    return (
                        <li className={styles.recommendLi}>
                            <div><img src={item.imageleft} /></div>
                            <div>
                                <div>
                                    <h3>{item.titll}</h3>
                                    <h5>{item.description}</h5>
                                    <ul className={styles.recommendTag}>
                                        <li>{item.tag1}</li>
                                        <li>{item.tag2}</li>
                                        <li>{item.tag3}</li>
                                        <li>{item.tag4}</li>
                                    </ul>
                                </div>
                            </div>
                            <div><img src={item.imageright} /></div>
                        </li>
                    )
                })
            }
        </ul>
    );
}

export default Recommend;
