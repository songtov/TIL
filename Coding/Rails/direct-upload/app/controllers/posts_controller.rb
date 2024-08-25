class PostsController < ApplicationController
    
    def index
        @posts = Post.order('created_at DESC')
        @rows = [[]]
        @posts.each do |post|
            if @rows.last.length < 3
                @rows.last << post
            else
                @rows << [post]
            end
        end
    end
end
