import styles from "./aboutUsSecond.module.css"
import History from "./history/history";
import News from "./news/news";
import Team from "./team/team";

const AboutUsSecond = () => {
    return (
        <div className={styles.secondpage}>
<History></History>
<Team></Team>
<News></News>
        </div>
    )
}
export default AboutUsSecond;