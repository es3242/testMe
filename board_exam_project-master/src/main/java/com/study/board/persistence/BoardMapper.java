package com.study.board.persistence;

import com.study.board.domain.BoardVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Insert({"<script>",
            "INSERT INTO board(title, content)",
            "VALUES(#{title}, #{content})",
            "</script>"})
    // 입력은 객체로 출력은 int로. 성공시 1, 실패시 0
    int insertBoard(BoardVO boardVO);

    @Select({"<script>",
            "SELECT * from board",
            "order by id desc",
            "<if test='offset != null and page_size != null'>",
            "LIMIT #{offset}, #{page_size}",
            "</if>",
            "</script>"})
    List<BoardVO> findBoard(@Param("offset") Integer offset, @Param("page_size") Integer page_size);


    @Select({"<script>",
            "SELECT * from board",
            "where id = #{id}",
            "</script>"})
    BoardVO findOneBoard(int id);

    @Select({"<script>",
            "SELECT count(*) from board",
            "</script>"})
    Integer countBoard();

    @Update({"<script>",
            "UPDATE board",
            "<trim prefix='set' suffixOverrides=','>",
            "<if test='title != null'>title = #{title},</if>",
            "<if test='content != null'>content = #{content},</if>",
            "</trim>",
            "WHERE id = #{id}",
            "</script>"})
    int updateBoard(BoardVO boardVO);

    @Delete({"<script>",
            "DELETE FROM board",
            "WHERE id = #{id}",
            "</script>"})
    int deleteBoard(int id);
}