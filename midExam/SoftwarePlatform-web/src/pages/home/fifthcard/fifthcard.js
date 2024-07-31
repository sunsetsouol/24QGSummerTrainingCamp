import styles from "./card.module.css"

import { Card } from 'antd';
const FifthCard = ({ image }) => {
    // console.log(image);
    return (
        <Card
            className={styles.card}
            hoverable style={{ width: '270px' }}>

            <div className={styles.picbox}
            // style={{ backgroundImage: `url(${image})` }}
            >
                <img className={styles.pic} src={image}></img>
            </div>

        </Card>
    )
}
export default FifthCard;