package com.baseball.security;

//public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
public class JsonAuthenticationFilter{
	
//	private AuthenticationManager authenticationManager;
//
//	public JsonAuthenticationFilter(AuthenticationManager authenticationManager){
//	    // AuthenticationManagerの設定
//	    this.authenticationManager = authenticationManager;
//	    // ログインパスを設定
//	    setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login","POST"));
//	    // ログイン用パラメータの設定
//	    setUsernameParameter("username");
//	    setPasswordParameter("password");
//
//	    // ログイン成功時はtokenを発行してレスポンスにセットする
//	    this.setAuthenticationSuccessHandler((req,res,ex) -> {
//	        // トークンの作成
//	        String token = JWT.create()
//	                .withIssuer("com.volkruss.toaru") //発行者
//	                .withClaim("username", ex.getName()) //keyに対してvalueの設定。汎用的な様々な値を保持できる
//	                .sign(Algorithm.HMAC256("secret")); // 利用アルゴリズムを指定してJWTを新規作成
//	        res.setHeader("X-AUTH-TOKEN", token); // tokeをX-AUTH-TOKENというKeyにセットする
//	        res.setStatus(200);
//	    });
//
//	    // ログイン失敗時
//	    this.setAuthenticationFailureHandler((req,res,ex) -> {
//	        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	    });
//	}
//
//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//	    try {
//	        ServletInputStream stream = request.getInputStream();
//	        // リクエストのjsonの値をUserFormにマッピングします。
//	        UserForm form = new ObjectMapper().readValue(request.getInputStream(), UserForm.class);
//	        // これでデフォルトのProviderを利用しつつ、ユーザーレコードの取得に関してはUserDetailsServiceの実装クラスのloadUserByUsernameを利用する
//	        return this.authenticationManager.authenticate(
//	                new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword(), new ArrayList<>())
//	        );
//	    } catch (IOException e) {
//	        throw new RuntimeException(e);
//	    }
//	}
}
