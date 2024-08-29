import styles from "./verifybill.module.css"
import React, { useState } from 'react';
import image1 from "../../assets/images/verifybill-first-part-pic.png"
import {Card ,Button} from "antd"
let descriptionData ='玩家可以独自探索、或与朋友并肩冒险，探索随机生成的世界，创造令人惊叹的奇迹。更有丰富的组件资源，帮助玩家开启多种创意玩法。你想玩的，这里都有。快拉上小伙伴一同加入冒险家大家庭，随时随地享受创造乐趣！欢乐的暑假，怎能少得了一场旅行？1.19&1.20.1双版本惊喜来袭，全新群系等你探索！幽匿群系的神秘诡谲、樱花群系的梦幻绮丽，还有荒野中的奇异生物和古迹废墟里鲜为人知的宝藏......世界大有不同！赶紧和小伙伴一起揭开未知的冒险篇章！'
const SoftwareName = ({description,image,name}) => {
   
    return (
        <div className={styles.softwarename}>
            <h3 className={styles.title}>{name?name:"我的世界"}</h3>
            <div className={styles.content}>
<div className={styles.pic} style={{backgroundImage:`url(${image?image:image1})`}}></div>
<div className={styles.right}>
    <div className={styles.righttop_card}>
<Card hoverable style={{width:'100%'} }className={styles.introduce}>
                            <p style={{ fontSize: '16px', fontFamily: 'Microsoft YaHei',letterSpacing:1 }}>
     {description?description:descriptionData}
    </p>
</Card>
    </div>
    <div className={styles.buy_btn}>
                        <Button className={styles.btn}>1,400CNY起</Button>
    </div>
</div>
            </div>
        </div>
    )
}
export default SoftwareName