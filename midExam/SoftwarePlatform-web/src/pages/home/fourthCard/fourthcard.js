import styles from "./card.module.css"
const FourthCard = () => {
    return (
        <div className={styles.fourthcard}>
            <div className={styles.pic}></div>
            <div className={styles.card_content}>
                <h6>用户</h6>
                <div className={styles.discription}>
                    <p>用户评价</p>

                </div>
                <div className={styles.score}>

                </div>
            </div>
        </div>
    )
}
export default FourthCard;