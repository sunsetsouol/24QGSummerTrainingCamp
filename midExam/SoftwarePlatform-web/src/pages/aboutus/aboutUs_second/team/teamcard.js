import styles from "./team.module.css"
const TeamCard=({image,name,job})=>{
    return(
        <div className={styles.teamcard}>
            <div className={styles.pic}>
                <img className={styles.images} src={image}></img>
            </div>
            <h3>丘俊凯</h3>
            <h6>前端大哥</h6>
        </div>
    )
}
export default TeamCard;