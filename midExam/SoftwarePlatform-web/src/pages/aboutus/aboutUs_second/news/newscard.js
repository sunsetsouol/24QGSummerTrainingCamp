import styles from "./news.module.css"

import { Card ,Button} from"antd" ;
const NewsCard = ({ image, name, job }) => {
    return (
        <Card className={styles.newscard} hoverable style={{ width: '372px' }}>
            <h3 className={styles.newstime}>2024-05-18</h3>
            <h3 className={styles.newstitle}>新闻标题</h3>
            <div className={styles.newscontent}>
                <p>国税总局发布了《支持协调发展税费优惠政策指引》。此次政策提及了216项税收优惠政策，尤其明确了支持消费提振信心的税费优惠政策，具有非常好的导向。</p>
            </div>
            <Button className={styles.learnmore}>阅读更多</Button>
        </Card>
    )
}
export default NewsCard;