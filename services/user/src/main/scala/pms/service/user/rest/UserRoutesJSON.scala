package pms.service.user.rest

import pms.json._

import pms.algebra.user._
import pms.service.user._

/**
  *
  * @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 26 Jun 2018
  *
  */
trait UserRoutesJSON extends PMSJson {

  implicit val userRoleCirceCodec: Codec[UserRole] = Codec.instance(
    encode = Encoder.apply[String].contramap(ur => ur.productPrefix),
    decode = Decoder.apply[String].emap(s => UserRole.fromName(s).left.map(_.message)),
  )

  implicit val userIDCirceCodec: Codec[UserID] = Codec.instance[UserID](
    encode = Encoder.apply[Long].contramap(UserID.despook),
    decode = Decoder.apply[Long].map(UserID.spook),
  )

  implicit val passwordResetTokenCirceCodec: Codec[PasswordResetToken] = Codec.instance[PasswordResetToken](
    encode = Encoder.apply[String].contramap(PasswordResetToken.despook),
    decode = Decoder.apply[String].map(PasswordResetToken.spook),
  )

  implicit val authenticationTokenCirceCodec: Codec[AuthenticationToken] = Codec.instance(
    encode = Encoder.apply[String].contramap(AuthenticationToken.despook),
    decode = Decoder.apply[String].map(AuthenticationToken.spook),
  )

  implicit val userRegistrationCirceCodec: Codec[UserRegistration] = derive.codec[UserRegistration]

  implicit val userCirceCodec: Codec[User] = derive.codec[User]

  implicit val pwResetReqCirceCodec: Codec[PasswordResetRequest] = derive.codec[PasswordResetRequest]

  implicit val pwResetComCirceCodec: Codec[PasswordResetCompletion] = derive.codec[PasswordResetCompletion]

  implicit val authCtxCirceCodec: Codec[AuthCtx] = derive.codec[AuthCtx]
}
