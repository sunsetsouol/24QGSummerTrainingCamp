import styles from "./third.module.css";
import ThirdCard from "../thirdcard/thirdcard";
import { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import { softwareRanking } from "../../../api";
import littleImage1 from '../../../assets/images/8420455527b2e3dbb19c1e32102a1286.png';
import littleImage2 from '../../../assets/images/8420455527b2e3dbb19c1e32102a1286.png';
import littleImage3 from '../../../assets/images/8420455527b2e3dbb19c1e32102a1286.png';
import littleImage4 from '../../../assets/images/8420455527b2e3dbb19c1e32102a1286.png';

const HomeThirdPage = () => {
    const [data, setData] = useState([
        {
            softwareImage: littleImage1,
            titll: '绝地潜兵',
            description: '玩家必须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌人心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌'

        },
        {
            softwareImage: littleImage2,
            titll: '麻布仔大冒险',
            description: '玩家必须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌人'

        }, {
            softwareImage: littleImage3,
            titll: '地平线 西之绝境',
            description: '玩家必须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌人'

        }, {
            softwareImage: littleImage4,
            titll: '原神',
            description: '玩家必须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌人'

        }, {
            softwareImage: littleImage4,
            titll: '原神',
            description: '玩家必须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌人'

        }, {
            softwareImage: littleImage4,
            titll: '原神',
            description: '玩家必须齐心协力保护超级地球，在浩大的星际战争中打败人类的敌人'

        }
    ])
    const navigate = useNavigate();

    useEffect(() => {
        async function receiveInformation() {
            try {
                const response = await softwareRanking();
                setData(response.data || data); // Ensure data is always an array
            } catch (error) {
                console.error('Error fetching software ranking:', error);
            }
        }
        receiveInformation();
    }, []);

    // Handle navigation to details page
    const goToDetails = (software) => {
        if (software) {
            const softwareId = software.softwareId;
            const encodedSoftwareId = encodeURIComponent(softwareId); // Encode software ID
            const url = `/header/verifybill?softwareId=${encodedSoftwareId}`; // Construct URL
            navigate(url);
            window.scrollTo(0, 0);
        }
    };

    // Determine the number of items to display
    const itemsToDisplay = data && data.length > 0 ? (data.length > 6 ? data.slice(0, 6) : data) : [];

    return (
        <div className={styles.home_thirdpage}>
            <h3 className={styles.title}>
                为什么选择我们
            </h3>
            <div className={styles.maincontent}>
                <h3>优质的软件</h3>
                <div className={styles.content}>
                    {itemsToDisplay.map((item, index) => (
                        <div key={index} onClick={() => goToDetails(item)}>
                            <ThirdCard data={item} />
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default HomeThirdPage;