import React, { useState, useCallback } from "react";
import axios from "axios";

const DragDrop2 = () => {
  const [files, setFiles] = useState([]);
  const [userId, setUserId] = useState(0);

  const handleFileChange = useCallback(
    (event) => {
      const selectedFiles = event.target.files;

      const updatedFiles = [...files];
      for (let i = 0; i < selectedFiles.length; i++) {
        updatedFiles.push(selectedFiles[i]);
      }

      setFiles(updatedFiles);
    },
    [files]
  );

  const handleUserIdChange = useCallback((event) => {
    setUserId(Number(event.target.value));
  }, []);

  const handleUpload = useCallback(async () => {
    if (files.length === 0) {
      alert("파일을 선택해주세요.");
      return;
    }

    const formData = new FormData();
    for (let i = 0; i < files.length; i++) {
      formData.append("pdfFile", files[i]);
    }
    formData.append("user.id", userId);

    try {
      const response = await axios.post("/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });

      console.log("업로드 완료:", response.data);
      // 리다이렉션 등 추가적인 처리를 수행할 수 있습니다.
    } catch (error) {
      console.error("업로드 실패:", error);
    }
  }, [files, userId]);

  return (
    <div>
      <h3>파일 첨부</h3>
      <input type="file" multiple onChange={handleFileChange} />
      <input type="number" value={userId} onChange={handleUserIdChange} />
      <button onClick={handleUpload}>업로드</button>
    </div>
  );
};

export default DragDrop2;
