import React, { useState, useEffect } from 'react';
import styles from './shop.module.css'
import littleImage1 from '../../assets/images/827e3278ed3118dabc56ae91afec721e.png';
import littleImage2 from '../../assets/images/8420455527b2e3dbb19c1e32102a1286.png';
import littleImage3 from '../../assets/images/bigImage.png';
import littleImage4 from '../../assets/images/d0e2d20ff686c5d06a5dae3d6149a639.png';

const BigLittleImage = ({ lastData, goToDetails }) => {
    const [data, setData] = useState([
        {
            image: littleImage1,
            titll: '绝地潜兵',
            tags: ['Moba', '多人', '开放世界'],
            score: '5.0',
            description: '玩家必须齐心协力保护超级地球，在浩大的保护超保护超级地球，在浩大的保护超级地球，在浩大保护超级地球，在浩大保护超级地球，在浩大星际战争中打败人类的敌人'

        },
        {
            image: littleImage2,
            titll: '麻布仔大冒险',
            tags: ['冒险', '多人', '开放世界'],
            score: '5.0',
            description: '玩家必须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌人'

        }, {
            image: littleImage3,
            titll: '地平线 西之绝境',
            tags: ['历险', '战斗', '多人'],
            score: '5.0',
            description: '玩家必须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌人'

        }, {
            image: littleImage4,
            titll: '原神',
            tags: ['二次元', '开放世界', '角色扮演'],
            score: '5.0',
            description: '玩家必须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌人'

        },
    ])

    const truncateText = (text, maxLength) => {
        if (text && text.length > maxLength) {
            return text.slice(0, maxLength) + '...';
        }
        return text;
    };

    useEffect(() => {
        if (lastData && lastData.length > 0) { // 确保 lastData 不为空

            const information = lastData.map((item) => {
                return {
                    softwareId: item.softwareId,
                    image: item.softwareImage,
                    titll: item.softwareName,
                    tags: item.tags || ['标签', '标签', '标签'], // 确保 tags 存在且是一个数组
                    score: '5.0',
                    description: item.description
                };
            }).slice(0, 4); // 只保留前四个元素

            setData(information);
        }
    }, [lastData]);


    const clickImg = (data) => {
        goToDetails(data)
    }

    return (
        <ul className={styles.smallImages}>
            {
                data.map((item, index) => {
                    return (
                        <li className={styles.smallImages_li} onClick={() => { clickImg(item) }}>

                            <div>
                                <img src={item.image} />
                            </div>
                            <div>
                                <h2>{item.titll}</h2>
                                <div className={styles.tages}>
                                    {item.tags && item.tags.length > 0 && (
                                        <ul>
                                            {item.tags.map((tag, index) => (
                                                <li key={index}>{tag}</li>
                                            ))}
                                        </ul>
                                    )}
                                    <ul></ul>
                                    <div>{item.score}</div>
                                </div>
                                <p style={{ color: '#C8C9CB', fontSize: '12px' }}>{truncateText(item.description, 35)}</p>
                            </div>

                        </li>
                    )

                })
            }
        </ul>
    );
}

export default BigLittleImage;
