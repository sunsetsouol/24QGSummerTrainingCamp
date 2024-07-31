import styles from "./card.module.css"
import { Card, Button } from 'antd';
const ThirdCard = ({ data }) => {
    return (
        <Card
            cover={
                data ? <div className={styles.pic} style={{ backgroundImage: `url(${data.softwareImage})` }}>
                </div> : <div className={styles.pic} >
                    </div>}
            className={styles.card}
            hoverable bordered={false} style={{ width: '370px', padding: '0' }}>
            <div className={styles.card_bottom}>
                <h3>{data ? data.softwareName : '未知'}</h3>
                <h5>软件描述</h5>
                <div className={styles.discription}>
                    <p>{data ? data.createTime : '未知'}</p>

                </div>
                <Button className={styles.more}>更多</Button>
            </div>
        </Card>
    )
}
export default ThirdCard;