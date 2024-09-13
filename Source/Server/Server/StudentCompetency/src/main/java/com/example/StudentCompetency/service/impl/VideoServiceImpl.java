package com.example.StudentCompetency.service.impl;

import com.example.StudentCompetency.service.VideoService;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    /*

    @Resource
    private VideoMapper videoMapper;
    @Autowired
    private S3Service s3Services;
    // Get all videos
    public ArrayList<Video> findAll() {
        return videoMapper.findAll();
    }

    // Find video by ID
    public int findPublisherId(Long id){
        return videoMapper.findPublisherId(id);
    }

    public Video findById(Long id) {
        Video video = videoMapper.findById(id);
        Integer pub = videoMapper.findPublisherId(id);
        if(video!=null){
        video.setPublisher(pub);
        }
        return video;
    }



    // Find video by title

    public Video findByTitle(String title) {
        return videoMapper.findByTitle(title);
    }

    // Save a new video
    @Override
    public void save(Video video) {
        videoMapper.save(video);
    }

    // Update video by ID
    @Override
    public void updateById(Video video) {
        videoMapper.updateById(video);
    }

    // Delete video by ID
    @Override
    public void deleteById(Long id) {
        videoMapper.deleteById(id);
    }

    public ArrayList<Video> getVideoListByUserId(Long id){
        ArrayList<Long> idList = new ArrayList<>(videoMapper.getVideoListByUserId(id));
        ArrayList<Video> videoList = new ArrayList<>();
        idList.forEach( videoId-> {
            videoList.add(findById(videoId));

        });
        videoList.removeIf(Objects::isNull);
        videoList.removeIf(video -> Long.valueOf(video.getPublisher()).equals(id));

        return videoList;
    }
    public ArrayList<Video> getRecordedListByUserId(Long id){
        return videoMapper.getRecordedListByUserId(id);
    }
    public Result<VideoVO> uploadVideoToS3(MultipartFile uploadFile, String tilte, Integer publisher){
        Video video = new Video();
        video.setPublisher(publisher);
        video.setTitle(tilte);
        videoMapper.save(video);
        video=videoMapper.findByTitle(tilte);
        Integer id=video.getId();
        s3Services.uploadFile(uploadFile,publisher.toString(),id.toString());
        String url="https://d1cfvedrf8mf2j.cloudfront.net/"+publisher+"/"+id+".mp4";
        video.setUrl(url);
        videoMapper.updateURL(video);
        videoMapper.addViewer(video.getId().longValue(),tilte,publisher.longValue());
        video.setCover(VideoUtils.fetchFrame(uploadFile));
        videoMapper.updateCover(video);

        VideoVO videoVO = new VideoVO();
        BeanUtils.copyProperties(video, videoVO);
        return Result.success(videoVO);
    }
    public Result<UserVO> addViewer(Video video, User user, User currentUser){
        video=videoMapper.findById(Long.valueOf(video.getId()));
        if (video!=null){
            if(video.getPublisher()==currentUser.getId()){
                videoMapper.addViewer(Long.valueOf(video.getId()),video.getTitle(),Long.valueOf(user.getId()));
                UserVO userVO=new UserVO();
                BeanUtils.copyProperties(user,userVO);
                return Result.success(userVO);
            }else return Result.error(ResultMsgEnum.FAIL.getCode(), ResultMsgEnum.FAIL.getMessage());
        }else return Result.error(ResultMsgEnum.FAIL.getCode(), ResultMsgEnum.FAIL.getMessage());
    }

    public Result<VideoVO> tempVideoSave(Video video){
        videoMapper.save(video);
        video=videoMapper.findByTitle(video.getTitle());
        if(video!=null){
            VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(video, videoVO);
            return Result.success(videoVO);
        }else
            return Result.error(ResultMsgEnum.FAIL.getCode(),ResultMsgEnum.FAIL.getMessage());
    }

     */
}
