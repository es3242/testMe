// https://itprogramming119.tistory.com/entry/React-%EC%9D%B4%EB%AF%B8%EC%A7%80-%ED%8C%8C%EC%9D%BC%EC%97%85%EB%A1%9C%EB%93%9C-%EB%AF%B8%EB%A6%AC%EB%B3%B4%EA%B8%B0-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
import { useState } from "react";

const Preview = () => {
  const [imageSrc, setImageSrc] = useState(null);

  const onUpload = (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);

    return new Promise((resolve) => {
      reader.onload = () => {
        setImageSrc(reader.result || null); // 파일의 컨텐츠
        resolve();
      };
    });
  };

  return (
    <div>
      <input
        accept="image/*"
        multiple
        type="file"
        onChange={(e) => onUpload(e)}
      />
      <img src={imageSrc} alt="" width={"100%"} />
    </div>
  );
};

export default Preview;
