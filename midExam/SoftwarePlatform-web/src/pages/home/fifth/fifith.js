
import FifthCard from "../fifthcard/fifthcard";
import styles from "./fifth.module.css"
import image1 from '../../../assets/images/Logo 1.png';
import image2 from '../../../assets/images/Logo 2.png';
import image3 from '../../../assets/images/Logo 3.png';
import image4 from '../../../assets/images/Logo 4.png';
import image5 from '../../../assets/images/Logo 5.png';
import image6 from '../../../assets/images/Logo 6.png';
import image7 from '../../../assets/images/Logo 7.png';
import image8 from '../../../assets/images/Logo 8.png';
const HomeFifthPage = () => {
    return (
        <div className={styles.home_fifthpage}>
            <h3 className={styles.maintitle}>
                受到多家组织信赖
            </h3>
            <div className={styles.content}>
                <FifthCard image={image1} num={1}></FifthCard>
                <FifthCard image={image2} num={2}></FifthCard>
                <FifthCard image={image3} num={3}></FifthCard>
                <FifthCard image={image4} num={4}></FifthCard>
                <FifthCard image={image5} num={5}></FifthCard>
                <FifthCard image={image6} num={6}></FifthCard>
                <FifthCard image={image7} num={7}></FifthCard>
                <FifthCard image={image8} num={8}></FifthCard>
            </div>
        </div>
    )
}
export default HomeFifthPage;