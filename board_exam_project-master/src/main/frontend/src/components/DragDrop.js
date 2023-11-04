// https://velog.io/@yiyb0603/React%EC%97%90%EC%84%9C-%EB%93%9C%EB%9E%98%EA%B7%B8-%EC%95%A4-%EB%93%9C%EB%A1%AD%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%9C-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C-%ED%95%98%EA%B8%B0
// https://dev.to/hexcube/how-to-add-sass-support-to-a-vite-react-app-37p

import axios from "axios";
import Button from "react-bootstrap/Button";
import { useNavigate } from "react-router-dom";

// DragDrop.tsx
import React, {
  useState,
  useCallback,
  useEffect,
  ChangeEvent,
  useRef,
} from "react";
import "./DragDrop.scss";

const DragDrop = () => {
  // 드래그 중일때와 아닐때의 스타일을 구분하기 위한 state 변수
  const [isDragging, setIsDragging] = useState(false);
  const [files, setFiles] = useState([]);
  const [uploadProgress, setUploadProgress] = useState(0);
  const navigate = useNavigate();

  // 드래그 이벤트를 감지하는 ref 참조변수 (label 태그에 들어갈 예정)
  const dragRef = useRef(null);

  // 각 선택했던 파일들의 고유값 id
  const fileId = useRef(0);

  const onChangeFiles = useCallback(
    (e) => {
      let selectFiles = [];
      let tempFiles = files;

      if (e.type === "drop") {
        selectFiles = e.dataTransfer.files;
      } else {
        selectFiles = e.target.files;
      }

      for (const file of selectFiles) {
        // 스프레드 연산자를 이용하여 기존에 있던 파일들을 복사하고, 선택했던 파일들을 append 해줍니다.
        tempFiles = [
          ...tempFiles,
          {
            id: fileId.current++, // fileId의 값을 1씩 늘려주면서 각 파일의 고유값으로 사용합니다.
            object: file, // object 객체안에 선택했던 파일들의 정보가 담겨있습니다.
          },
        ];
      }

      setFiles(tempFiles);
    },
    [files]
  ); // 위에서 선언했던 files state 배열을 deps에 넣어줍니다.

  const handleFilterFile = useCallback(
    (id) => {
      // 매개변수로 받은 id와 일치하지 않는지에 따라서 filter 해줍니다.
      setFiles(files.filter((file) => file.id !== id));
    },
    [files]
  );

  const handleUpload = async () => {
    if (files.length === 0) {
      alert("파일을 선택해주세요.");
      return;
    }

    const formData = new FormData();
    formData.append("pdfFile", files[0].object);
    formData.append("user.id", sessionStorage.getItem("loggedIn"));
    console.log(files[0]);
    const fileName = files[0].object.name;

    try {
      const response = await axios.post("/pdfup1", formData, {
        headers: {
          "Content-Type": "multipart/form-data", // multipart/form-data로 설정
        },
      });

      console.log("업로드 완료:", response.data);
      navigate(`/make/`, { state: { fileName } });
    } catch (error) {
      console.error("업로드 실패:", error);
      alert("업로드에 실패했습니다!");
    }
  };

  const handleDragIn = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
  }, []);

  const handleDragOut = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();

    setIsDragging(false);
  }, []);

  const handleDragOver = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();

    if (e.dataTransfer.files) {
      setIsDragging(true);
    }
  }, []);

  const handleDrop = useCallback(
    (e) => {
      e.preventDefault();
      e.stopPropagation();

      onChangeFiles(e);
      setIsDragging(false);
    },
    [onChangeFiles]
  );

  const initDragEvents = useCallback(() => {
    // 앞서 말했던 4개의 이벤트에 Listener를 등록합니다. (마운트 될때)

    if (dragRef.current !== null) {
      dragRef.current.addEventListener("dragenter", handleDragIn);
      dragRef.current.addEventListener("dragleave", handleDragOut);
      dragRef.current.addEventListener("dragover", handleDragOver);
      dragRef.current.addEventListener("drop", handleDrop);
    }
  }, [handleDragIn, handleDragOut, handleDragOver, handleDrop]);

  const resetDragEvents = useCallback(() => {
    // 앞서 말했던 4개의 이벤트에 Listener를 삭제합니다. (언마운트 될때)

    if (dragRef.current !== null) {
      dragRef.current.removeEventListener("dragenter", handleDragIn);
      dragRef.current.removeEventListener("dragleave", handleDragOut);
      dragRef.current.removeEventListener("dragover", handleDragOver);
      dragRef.current.removeEventListener("drop", handleDrop);
    }
  }, [handleDragIn, handleDragOut, handleDragOver, handleDrop]);

  useEffect(() => {
    initDragEvents();

    return () => resetDragEvents();
  }, [initDragEvents, resetDragEvents]);

  return (
    <div className="DragDrop">
      <input
        type="file"
        id="fileUpload"
        style={{ display: "none" }} // label을 이용하여 구현하기에 없애줌
        multiple={true} // 파일 다중선택 허용
        onChange={onChangeFiles}
      />

      <label
        className={isDragging ? "DragDrop-File-Dragging" : "DragDrop-File"}
        // 드래그 중일때와 아닐때의 클래스 이름을 다르게 주어 스타일 차이
        htmlFor="fileUpload"
        ref={dragRef}
      >
        <div>파일 첨부</div>
      </label>
      <div className="DragDrop-Files">
        {files.length > 0 &&
          files.map((file) => {
            const {
              id,
              object: { name },
            } = file;

            return (
              <div key={id}>
                <div>{name}</div>
                <div
                  className="DragDrop-Files-Filter"
                  onClick={() => handleFilterFile(id)}
                  // onClick 속성에 위처럼 함수를 추가시켜 줍니다.
                >
                  X
                </div>
              </div>
            );
          })}
      </div>
      <Button
        variant="primary"
        type="submit"
        onClick={handleUpload}
        className="mt-3"
      >
        업로드
      </Button>
    </div>
  );
};

export default DragDrop;
