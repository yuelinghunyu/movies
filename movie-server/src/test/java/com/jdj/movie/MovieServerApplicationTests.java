package com.jdj.movie;

import com.jdj.movie.bll.MovieBll;
import com.jdj.movie.bll.UsersBll;
import com.jdj.movie.enums.StaticTypes;
import com.jdj.movie.model.Movie;
import com.jdj.movie.model.Users;
import com.jdj.movie.utils.Md5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServerApplicationTests {
	@Autowired
	private MovieBll movieBll;
	@Autowired
	private UsersBll usersBll;

	@Test
	public void contextLoads() {
	}
	@Test
	public void testInsertMovie(){

		Movie movie = new Movie();
		movie.setId(UUID.randomUUID().toString().replace("-","").toLowerCase());
		movie.setArea(3);
		movie.setPicUrl("http://n.sinaimg.cn/ent/transform/487/w210h277/20180628/U-Sx-heqpwqy3257323.jpg");
		movie.setContent("http://n.sinaimg.cn/ent/transform/487/w210h277/20180628/U-Sx-heqpwqy3257323.jpg");
		movie.setDescription(" 一事无成的郑开司，参加了一场游轮上的神秘游戏来偿还巨款借款，游戏参与者要用“石头，剪刀，布”扑克为道具，夺取对手的星星标志。局中局、计中计");
		movie.setTitle("动物世界");
		movie.setType(1);
		movie.setPrice(new BigDecimal(2.01));
		movie.setCount(4);
		movie.setMovieType(StaticTypes.valueOf(3));
		movie.setIsFree(0);

		int flag = movieBll.insertMovie(movie);

	}
	@Test
	public void testUUID(){
		String id =  UUID.randomUUID().toString().replace("-", "").toLowerCase();
		System.out.print(id);
	}

	@Test
	public void testMd5(){
		String password1 = "Jdj12345";
		String password2 = "Fxx12345";
		String md51 = Md5Utils.getMD5(password1);
		String md52 = Md5Utils.getMD5(password2);
		System.out.print(md51);
		System.out.print(md52);
	}
	@Test
	public void testLikeSearchMovie(){
		String title = "乐";
		List<Movie> list = movieBll.getMovieListLike(title,0,10);
		int count = movieBll.getMovieListLikeCount(title);
	}

	@Test
	public void testInsertUser(){
		String wechatId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		String wechatName = "四叶草";
		String wechatLogo = "http://yuelinghunyu.oss-cn-beijing.aliyuncs.com/data/1543933272768.jpg?Expires=1859293270&OSSAccessKeyId=LTAIuw9k1cverkbk&Signature=c5tSu2FLVBfdZHw6UsuBFokAi/A%3D";
		Users users = new Users();
		users.setWechatId(wechatId);
		users.setWechatName(wechatName);
		users.setWechatLogo(wechatLogo);
		usersBll.insertUserItem(users);
	}

}
