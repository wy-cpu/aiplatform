package com.demo.model.movie;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ai_movie")
public class MovieInfo {

    /**主键*/
    /***
     * @GeneratedValue注解的strategy属性提供四种值：
     * –AUTO： 主键由程序控制，是默认选项，不设置即此项。
     * –IDENTITY：主键由数据库自动生成，即采用数据库ID自增长的方式，Oracle不支持这种方式。
     * –SEQUENCE：通过数据库的序列产生主键，通过@SequenceGenerator 注解指定序列名，mysql不支持这种方式。
     * –TABLE：通过特定的数据库表产生主键，使用该策略可以使应用更易于数据库移植。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 电影名称
     */
    @Column(name = "movieName", unique = true, nullable = true, length = 255)
    private String movieName;

    /**
     * 电影评分
     */
    @Column(name = "score", unique = true, nullable = true, length = 10)
    private Double score;

    /**
     * 描述
     */
    @Column(name = "movieDescribe", unique = true, nullable = true, length = 255)
    private String movieDescribe;

    /**
     * 海报路径
     */
    @Column(name = "posterPath", unique = true, nullable = true, length = 255)
    private String posterPath;

    /**
     * 海报旧路径
     */
    @Column(name = "posterOldPath", unique = true, nullable = true, length = 255)
    private String posterOldPath;

    /**
     * 上线时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "onlineTime", nullable = true)
    private Date onlineTime;

    /**
     * 录入时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "entryTime", nullable = true)
    private Date entryTime;

    /**
     * 修改时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "updateTime", nullable = true)
    private Date updateTime;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "createTime", nullable = true)
    private Date createTime;

		/*//地区表
		private AreaInfo areaInfo;

		//时间表
		private TimeInfo timeInfo;

		//类型表
		private TypeInfo typeInfo;

		//附件路径
		private String attachPaths;

		//附件名称
		private String attachNames;

		//附件表
		private AttachInfo attachInfo;

		//电影表
		private String movieInfoIds;*/

		/*//最大上线时间
		private Date maxOnlineTime;
		
		//最小上线时间
		private Date minOnlineTime;
		
		//最大录入时间
		private Date maxEntryTime;
		
		//最小录入时间
		private Date minEntryTime;
		
		//最大评分
		private Double maxScore;
		
		//最小评分
		private Double minScore;
		
		//批量逻辑删除选中的ids
		private String ids;*/

    public String getPosterOldPath() {
        return posterOldPath;
    }

    public void setPosterOldPath(String posterOldPath) {
        this.posterOldPath = posterOldPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getMovieDescribe() {
        return movieDescribe;
    }

    public void setMovieDescribe(String movieDescribe) {
        this.movieDescribe = movieDescribe;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

		/*public String getMovieInfoIds() {
			return movieInfoIds;
		}

		public void setMovieInfoIds(String movieInfoIds) {
			this.movieInfoIds = movieInfoIds;
		}

		public AttachInfo getAttachInfo() {
			return attachInfo;
		}

		public void setAttachInfo(AttachInfo attachInfo) {
			this.attachInfo = attachInfo;
		}

		public String getAttachPaths() {
			return attachPaths;
		}

		public void setAttachPaths(String attachPaths) {
			this.attachPaths = attachPaths;
		}

		public String getAttachNames() {
			return attachNames;
		}

		public void setAttachNames(String attachNames) {
			this.attachNames = attachNames;
		}*/

		/*public TypeInfo getTypeInfo() {
			return typeInfo;
		}

		public void setTypeInfo(TypeInfo typeInfo) {
			this.typeInfo = typeInfo;
		}

		public TimeInfo getTimeInfo() {
			return timeInfo;
		}

		public void setTimeInfo(TimeInfo timeInfo) {
			this.timeInfo = timeInfo;
		}

		public AreaInfo getAreaInfo() {
			return areaInfo;
		}

		public void setAreaInfo(AreaInfo areaInfo) {
			this.areaInfo = areaInfo;
		}*/

		/*public String getIds() {
			return ids;
		}

		public void setIds(String ids) {
			this.ids = ids;
		}

		public Double getMaxScore() {
			return maxScore;
		}

		public void setMaxScore(Double maxScore) {
			this.maxScore = maxScore;
		}

		public Double getMinScore() {
			return minScore;
		}

		public void setMinScore(Double minScore) {
			this.minScore = minScore;
		}

		public Date getMaxEntryTime() {
			return maxEntryTime;
		}

		public void setMaxEntryTime(Date maxEntryTime) {
			this.maxEntryTime = maxEntryTime;
		}

		public Date getMinEntryTime() {
			return minEntryTime;
		}

		public void setMinEntryTime(Date minEntryTime) {
			this.minEntryTime = minEntryTime;
		}

		public Date getMaxOnlineTime() {
			return maxOnlineTime;
		}

		public void setMaxOnlineTime(Date maxOnlineTime) {
			this.maxOnlineTime = maxOnlineTime;
		}

		public Date getMinOnlineTime() {
			return minOnlineTime;
		}

		public void setMinOnlineTime(Date minOnlineTime) {
			this.minOnlineTime = minOnlineTime;
		}*/
}
