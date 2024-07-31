import styles from "./news.module.css"
import NewsCard from "./newscard";
const News = () => {
    return (
        <div className={styles.news}>
            <h3 className={styles.title}>新闻</h3>
            <div className={styles.content}>
<NewsCard></NewsCard>
<NewsCard></NewsCard>
<NewsCard></NewsCard>
            </div>
        </div>
    )
}
export default News;