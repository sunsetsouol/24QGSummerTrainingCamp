import styles from "./verifybill.module.css"
import { Card,List,Button } from "antd"
const data = [
    '我的世界 V2.00',
    '我的世界 V1.00',
    '我的世界 V2.00',
    '我的世界 V2.0.1',
    '我的世界 V2.00',
    '我的世界 V1.0.0',
];
const Version = () => {
    return (
        <div className={styles.version}>
            <h3 className={styles.title}>应用版本</h3>
            <div className={styles.version_content}>
                <Card style={{ width: '100%' }}
                    hoverable
                    className={styles.versioncard}>
                        <div className={styles.version_cardbox}>
                        <List
                            size="large"
                            dataSource={data}
                            renderItem={(item) => <List.Item><div className={styles.information}> 
                            <span className={styles.name}>{item}</span><span className={styles.version_time}>2024-06-02</span>
                            <Button className={styles.download_btn}>下载</Button></div></List.Item>}
                        />
                        </div>
                   
                </Card>
            </div>
        </div>
    )
}
export default Version;