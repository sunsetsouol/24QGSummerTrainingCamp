import React from 'react';
import styles from './shop.module.css';
import BigLittleImage from './bigLittleImage';
import EveryoneDownLoad from './everyoneDownLoad';
import Recommend from './recommend';
import Count from './count';
import Carousel from './carousel'
import { useEffect, useState } from 'react'
import { softwareRanking } from "../../api"
import { useNavigate } from "react-router-dom"

export default function Index() {
    const [data, setData] = useState([])
    const [lastData, setLastData] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        async function receiveInformation() {
            try {
                const response = await softwareRanking()
                setData(response)
                const sortedResponse = response.data.sort((b, a) => new Date(a.createTime) - new Date(b.createTime));
                setLastData(sortedResponse)
            } catch (error) {
                console.error('Error sending verification code:', error);
            }

        }
        receiveInformation()
    }, [])

    const goToDetails = (data) => {
        if (data && data.softwareId) {
            const softwareId = data.softwareId;
            const encodedSoftwareId = encodeURIComponent(softwareId); // 编码软件ID
            const url = `/header/verifybill?softwareId=${encodedSoftwareId}`; // 构建URL
            navigate(url);
            window.scrollTo(0, 0);
        }
    };


    return (
        <div className={styles.shop}>
            <h3 className={styles.todayfind}>今日发现</h3>
            <div className={styles.bigImage}>

                <Carousel lastData={lastData} goToDetails={goToDetails} />
            </div>
            <BigLittleImage lastData={lastData} goToDetails={goToDetails} />
            <h3 className={styles.todayfind}>大家都在下载</h3>
            <EveryoneDownLoad goToDetails={goToDetails} />
            <h3 className={styles.todayfind}>推荐</h3>
            <Recommend goToDetails={goToDetails} />
            <h3 className={styles.todayfind}>现实优惠</h3>
            <Count goToDetails={goToDetails} />
        </div>
    );
}