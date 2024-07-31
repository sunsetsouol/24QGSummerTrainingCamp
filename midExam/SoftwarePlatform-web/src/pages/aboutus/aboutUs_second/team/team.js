import styles from "./team.module.css"
import TeamCard from "./teamcard";
import image1 from "../../../../assets/images/qiujunkai.png"
const Team = () => {
    return (
        <div className={styles.team}>
            <h3>我们的团队</h3>
            <div className={styles.content}>
                <TeamCard image={image1}></TeamCard>
                <TeamCard image={image1}></TeamCard>
                <TeamCard image={image1}></TeamCard>
                <TeamCard image={image1}></TeamCard>
                <TeamCard image={image1}></TeamCard>
                <TeamCard image={image1}></TeamCard>
                <TeamCard image={image1}></TeamCard>
                <TeamCard image={image1}></TeamCard>
                
            </div>
        </div>
    )
}
export default Team;