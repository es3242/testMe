import threading
import json
import pandas as pd
# import tensorflow as tf
import torch

from BotServer import BotServer 
# from utils.Preprocess import Preprocess
# from utils.FindAnswer import FindAnswer
# from models.intent.IntentModel import IntentModel
# from train_tools.qna.create_embedding_data import create_embedding_data

# 로그 기능 구현
from logging import handlers
import logging

#log settings
LogFormatter = logging.Formatter('%(asctime)s,%(message)s')

#handler settings
LogHandler = handlers.TimedRotatingFileHandler(filename='./logs/chatbot.log', when='midnight', interval=1, encoding='utf-8')
LogHandler.setFormatter(LogFormatter)
LogHandler.suffix = "%Y%m%d"

#logger set
Logger = logging.getLogger()
Logger.setLevel(logging.ERROR)
Logger.addHandler(LogHandler)

# use logger example
# Logger.info("car is coming")

def to_client(conn, addr):
    try:
        # 데이터 수신
        read = conn.recv(2048) # 수신 데이터가 있을 때까지 블로킹
        print('======================')
        print('Connection from: %s' % str(addr))

        if read is None or not read:
            # 클라이언트 연결이 끊어지거나 오류가 있는 경우
            print('클라이언트 연결 끊어짐')
            exit(0)  # 스레드 강제 종료

        # json 데이터로 변환
        recv_json_data = json.loads(read.decode()) #받음
        print("데이터 수신 : ", recv_json_data)
        query = recv_json_data['Query']
        
        send_json_data_str = {
            "Query": query,
            "Answer": query,
            "imageUrl": query,
            "Intent": query
        }
        message = json.dumps(send_json_data_str) # json객체 문자열로 반환
        conn.send(message.encode()) # 응답 전송

    except Exception as ex:
        print(ex)

if __name__ == '__main__':
    # 봇 서버 동작
    port = 5050
    listen = 1000
    bot = BotServer(port, listen)
    bot.create_sock()
    print("bot start..")

    while True:
        conn, addr = bot.ready_for_client()
        client = threading.Thread(target=to_client, args=(
            conn,   # 클라이언트 연결 소켓
            addr,   # 클라이언트 연결 주소 정보
        ))
        client.start()
