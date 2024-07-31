import Information from "./information"
import MyMessage from "./message"
import Bought from "./isbought"
import styles from "./person.module.css"

const Person=()=>{
    return(
        <div className={styles.person_page}>
            <Information></Information>
            <MyMessage></MyMessage>
            <Bought></Bought>
        </div>
    )
}
export default Person