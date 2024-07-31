import styles from "./person.module.css"
import ModalAlert from "./uploadmodal";
import { Card, Avatar, List, Input, Button,Modal,Alert } from "antd"
import { UserOutlined } from '@ant-design/icons';
import { useState, useEffect,useRef } from "react";
import { updateUserInfo, getFingerprint, insertFingerprint, deleteFingerprint } from "../../api"
const Information = () => {
    const data = [
        'Racing car sprays burning fuel into crowd.',
        'Japanese princess to wed commoner.',
        'Australian walks 100km after outback crash.',
        'Man charged over missing wedding girl.',
        'Los Angeles battles huge wildfires.',
    ];
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [isFingerprintModalOpen, setIsFingerprintModalOpen] = useState(false);
    const [alert,setAlert]=useState(false)
    const [userId, setUserId] = useState(localStorage.getItem('userIdSf'))
    const [email, setEmail] = useState(localStorage.getItem('emailSf'))
    const [myDescription, setMyDescription] = useState(localStorage.getItem('myDescription'))
    const [image, setImage] = useState(localStorage.getItem('userImage'))
    const [imageFiles, setImageFiles] = useState({})
    const [changeImage, setChangeImage] = useState(false)
    const [userName, setUserName] = useState(localStorage.getItem('userName'))    

// 存硬件指纹信息
const [fingerData,setFingerData]=useState(null)
const [addState,setAddState]=useState(false)
    // const [disabled, setDisabled] = useState(true);
   
    const userNameRef=useRef(null)
    const descriptionRef=useRef(null)
    const imageRef=useRef(null)
    // 展示编辑个人信息页面弹窗
    const showModal = async () => {
        setIsModalOpen(true);
      
    };
    const handleOk = async() => {
        // console.log(userNameRef.current.input);
        // 获取最新的输入值  
        const newUserName = userNameRef.current.input.value;
        const newDescription = descriptionRef.current.input.value;

        // 更新状态  
        if (newUserName) setUserName(newUserName);
        if (newDescription) setMyDescription(newDescription);
        if (!newUserName && !newDescription && !changeImage) {
            return;
        } 
        // 清空输入框  
        if (newUserName  || newDescription || changeImage) {
            userNameRef.current.input.value = '';
            descriptionRef.current.input.value = '';
        } 
        try {
            const response = await updateUserInfo(userName, myDescription, userId,imageFiles);
            { newUserName && localStorage.setItem('userName', newUserName); } 
            {newDescription&&localStorage.setItem('myDescription', newDescription);}
            localStorage.setItem('userImage', image); // 确保 image 是最新的值  

            // console.log(response);
           
        } catch (error) {
            console.error('Error fetching models:', error);
           
        } finally {
            // 清空输入框  
            userNameRef.current.input.value = ''
            descriptionRef.current.input.value =''
            setIsModalOpen(false);
        }  

    };
    
    const handleCancel = () => {
        setIsModalOpen(false);
    };
    // 展示管理指纹页面弹窗
    const showFingerprintModal = () => {
        setIsFingerprintModalOpen(true);
        setAddState(false)
    };
    const handleFingerprintOk = () => {
        setIsFingerprintModalOpen(false);
    };
    const handleFingerprintCancel = () => {
        setIsFingerprintModalOpen(false);
    };
    const [userinformation, setUserinformation] = useState([
        {
            key: '用户名：',
            text: `${userName? userName : '帅哥'}`
        },
        {
            key: '邮箱：',
            text: `${email ? email : '1311471185@qq.com'}`
        },
        {
            key: '个性签名：',
            text: myDescription
        },
    ]);
    useEffect(() => {
        setUserinformation([
            {
                key: '用户名：',
                text: `${userName ? userName : '美女'}`
            },
            {
                key: '邮箱：',
                text: `${email ? email : '1311471185@qq.com'}`
            },
            {
                key: '个性签名：',
                text: `${myDescription ? myDescription : '热衷于工业软件开发，每天都被薄纱'}`
            },
        ])
        
    }, [userName,myDescription,image]); 
    const inputClick = (e) => {
        const file = e.target.files[0];
        // console.log(e.target.files[0]);
        setImageFiles(file)
        if (file) {
            // setImage(file);

            const reader = new FileReader();
            setChangeImage(true)
            reader.onloadend = () => {
                setImage(reader.result);
            };
            reader.readAsDataURL(file);
        }
    }; 
    const fingerNameRef = useRef(null)
    const fingerDetailRef = useRef(null)
    // 弹出框显示
    const alertshow=(setter)=>{
        setter(true);
        setTimeout(()=>{
            setter(false);
        },3000);
    }
// 显示指纹信息
    const showFingerprintDetail= async ()=>{
        try {
            const response = await getFingerprint(userId);
            // console.log(response.data);
            setFingerData(response.data)

        } catch (error) {
            console.error('Error fetching models:', error);

        }
    }
    // 下载指纹工具
    const downloadTools=()=>{
        const url = 'https://faken.oss-cn-guangzhou.aliyuncs.com/GetCpu.exe';
        const link = document.createElement('a');
        link.href = url;
        link.download = '指纹信息工具'; // 设置 download 属性，可以指定下载时的文件名  
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    }
    // 增加硬件指纹
    const addFinger=()=>{
setAddState(true)
    }
    const addFingerDetail=async ()=>{
        if (fingerNameRef.current.input.value && fingerDetailRef.current.input.value){
            try {
                const response = await insertFingerprint(userId, fingerNameRef.current.input.value, fingerDetailRef.current.input.value);
                fingerData.push({
                    hardwareName: fingerNameRef.current.input.value,
                    userFingerprintId: fingerDetailRef.current.input.value,
                })
                setFingerData(fingerData)
                console.log(response);
               

            } catch (error) {
                console.error('Error fetching models:', error);

            }
            setAddState(false)
        }else{
            alertshow(setAlert)
            
        }
        
    }
    // 删除硬件指纹
    const deleteFinger = async (userFingerprintId)=>{
        // console.log(111);
        try {
            const response = await deleteFingerprint(userFingerprintId);
            // console.log(response);
            let arr = fingerData.filter(obj => obj.userFingerprintId !== userFingerprintId);
            if (arr.length === 0) {
                setFingerData(null)
            }
            else {
                setFingerData(arr)
            }

        } catch (error) {
            console.error('Error fetching models:', error);

        }
        
        // console.log(arr);
      
      
    }
    return (
        <div className={styles.information_part}>
            <h3 className={styles.title}>个人信息</h3>
            <ModalAlert></ModalAlert>
            <div className={styles.information_box}>
                <Card hoverable style={{ width: '773px' }}
                    className={styles.information_card}
                >
                    <Button className={styles.change_message} onClick={showModal}>编辑个人资料</Button>
                    <div className={styles.message_box}>
                        <div className={styles.headbox}>
                            <Avatar size={150} icon={<UserOutlined />} src={image} />
                        </div>
                        <div className={styles.mymessage}>
                            <List
                                className={styles.mylist_message}
                                // className="mylist_message"
                                dataSource={userinformation}
                                renderItem={(item, index) => (
                                    <List.Item style={{ border: 'none', whiteSpace: 'nowrap' }}>
                                        <span className={styles.text_key}>{item.key}{item.text}</span>
                                

                                    </List.Item>
                                )}
                            />
                            <Button className={styles.myfingerprint_btn} 
                            onClick={()=>{
                                showFingerprintDetail();
                                showFingerprintModal();

                                }}>管理我的指纹信息</Button>
                            {/* 编辑个人信息 */}
                            <Modal title="修改个人信息" open={isModalOpen} onOk={handleOk} onCancel={handleCancel}>
                               <div className={styles.change_message_modal}>
                                    <div style={{ width: '150px', height: '150px', position: 'relative', }}>
                                        <Avatar size={150} icon={<UserOutlined />} src={image}/>
                                        <Input type="file" style={{
                                            zIndex: '100',
                                            position: 'absolute',
                                            width: '150px',
                                            height: '150px',
                                            borderRadius: '150px',
                                            right:'0',
                                            opacity: 0,
                                            cursor: 'pointer'
                                        }} ref={imageRef} onChange={inputClick} accept="image/*" ></Input>
                                </div>
                                    
                                    <Input className={styles.change_input} placeholder={userName} ref={userNameRef}   ></Input>
                                    <Input className={styles.change_input} placeholder={myDescription} ref={descriptionRef}  ></Input>
                               </div>
                            </Modal>
                            {/* 编辑硬件指纹信息 */}
                            <Modal title="管理我的硬件指纹信息" open={isFingerprintModalOpen} onOk={handleFingerprintOk} onCancel={handleFingerprintCancel}>
                                {alert && <Alert message="指纹信息和指纹名字不能为空" type="error" showIcon />}
                               <div className={styles.fingerprint}>
                                    {!fingerData && <div className={styles.finger_cue}>无硬件指纹请上传</div>}
                                    {fingerData && <List
                                        size="large"
                                        className={styles.fingerlist}
                                        bordered
                                        dataSource={fingerData ? fingerData : data}
                                        renderItem={(item, index) => <List.Item>
                                            <div className={styles.fingerbox}>
                                                <h3 className={styles.fingerkey}>{item.hardwareName}:</h3>
                                                <h3 className={styles.fingerdetail}>{item.userFingerprintId}</h3>
                                                <Button className={styles.fingerdelete} type="primary" onClick={() => deleteFinger(item.userFingerprintId)}>删除</Button>
                                            </div>
                                        </List.Item>}
                                    />}
                                   
                                    <div className={styles.add_btn_box}>
                                        <Button type="primary" className={styles.add} onClick={addFinger}>增加指纹</Button>
                                        <Button type="primary" className={styles.tools} onClick={downloadTools}>下载指纹工具</Button>
                                    </div>
                                    {addState && <div className={styles.add_detail}>
                                        <Input placeholder="指纹名" className={styles.add_input} ref={fingerNameRef}></Input>
                                        <Input placeholder="指纹信息" className={styles.add_input} ref={fingerDetailRef}></Input>
                                        <Button type="primary" className={styles.to_add} onClick={addFingerDetail}>添加</Button>
                                    </div>}
                                   
                               </div>
                            </Modal>
                        </div>

                    </div>
                </Card>
            </div>
        </div>
    )
}
export default Information