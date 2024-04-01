package shop.mtcoding.blog.model.reply;

import lombok.Data;
import shop.mtcoding.blog.model.board.Board;
import shop.mtcoding.blog.model.user.User;

public class ReplyRequest {
    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String comment;

        public Reply toEntity(User sessionUser, Board board) {
            return Reply.builder()
                    .comment(comment)
                    .board(board)
                    .user(sessionUser)
                    .build();
        }
    }
}
