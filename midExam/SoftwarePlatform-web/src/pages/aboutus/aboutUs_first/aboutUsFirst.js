import styles from "./aboutUsFirst.module.css"
const AboutUsFirst=()=>{
    return(
        <div className={styles.firstpage}>
            <div className={styles.content_box}>
                <h3 className={styles.top}>诞生于1996<br />
                    梦想做产业领袖</h3>
                <div className={styles.bottom}>
                    <h3>The Drive to Develop</h3>
                    <h6> 代码赋予我们激情。成立以来，我们一直在致力于打造世界上对用户最友好、最实用的产品。
                        我们相信，让大家自由地做自己想做的事情会带来最好的结果。 面对的每一个问题都应被视为新的挑战、改进和创新的机会。</h6>
                </div> </div>
           
        </div>
    )
}
export default AboutUsFirst;