import React from 'react';
import styles from './bill.module.css'
import { Input } from 'antd';
const { TextArea } = Input;



const textarea = () => {
    return (
        <div className={styles.textareaDiv}>
            <TextArea rows={6} placeholder="Enter your comment" maxLength={100} style={{ resize: 'none', fontSize: '16px' }} />
        </div>
    );
}

export default textarea;
